<template>

  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
    <!--用户数据-->
    <el-col :span="20" :xs="24">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="healthItemName">
        <el-input
          v-model="queryParams.healthItemName"
          placeholder="请输入项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['health:item:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['health:item:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['health:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['health:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="healthItemId" />
      <el-table-column label="部门" align="center" prop="deptName" />
      <el-table-column label="项目名称" align="center" prop="healthItemName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['health:item:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['health:item:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    </el-col>
    </el-row>
    <!-- 添加或修改重大阳性项目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="归属部门" prop="deptId">
          <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
        </el-form-item>
        <el-form-item label="项目名称" prop="healthItemName">
          <el-input v-model="form.healthItemName" placeholder="请输入项目名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listItem, getItem, delItem, addItem, updateItem, exportItem } from "@/api/health/item";
import {treeselect} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {getUserProfile} from "@/api/system/user";
export default {
  name: "Item",
  components: {Treeselect},
  data() {
    return {
      // 部门树选项
      deptOptions: undefined,
      // 部门名称
      deptName: undefined,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 重大阳性项目表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        healthItemName: null,
      },
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
        deptId: [
          { required: true, message: "部门ID不能为空", trigger: "blur" }
        ],
        healthItemName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
watch: {
  // 根据名称筛选部门树
  deptName(val) {
    this.$refs.tree.filter(val);
  }
},
  created() {
    this.getUser();
    this.getTreeselect();
  },
  methods: {
    /** 查询部门下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList(data.id);
    },
    getUser(){
      getUserProfile().then(response => {
        //this.queryParams.deptId=response.data.deptId;
        this.user=response.data;
        this.getList(this.user.deptId);
      });
    },
    /** 查询重大阳性项目列表 */
    getList(deptId) {
      this.loading = true;
      this.queryParams.params = {};
      this.queryParams.deptId=deptId;
      listItem(this.queryParams).then(response => {
        this.itemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        healthItemId: null,
        deptId: null,
        healthItemName: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList(this.user.deptId);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.healthItemId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect();
      this.open = true;
      this.title = "添加重大阳性项目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const healthItemId = row.healthItemId || this.ids
      getItem(healthItemId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改重大阳性项目";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.healthItemId != null) {
            updateItem(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItem(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const healthItemIds = row.healthItemId || this.ids;
      this.$confirm('是否确认删除重大阳性项目编号为"' + healthItemIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delItem(healthItemIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有重大阳性项目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportItem(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
