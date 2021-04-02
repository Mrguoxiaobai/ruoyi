<template>
  <div class="app-container">
    <el-row :gutter="10">
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
          <el-form-item label="体检号" prop="infoCode">
            <el-input
              v-model="queryParams.infoCode"
              placeholder="请输入体检号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="身份号" prop="infoCardno">
            <el-input
              v-model="queryParams.infoCardno"
              placeholder="请输入身份号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="姓名" prop="infoName">
            <el-input
              v-model="queryParams.infoName"
              placeholder="请输入姓名"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="年龄" prop="infoAge">
            <el-input
              v-model="queryParams.infoAge"
              placeholder="请输入年龄"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="性别" prop="infoSex">
            <el-select v-model="queryParams.infoSex" placeholder="请选择性别" clearable size="small">
              <el-option
                v-for="dict in infoSexOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="科室名称" prop="deptId">
            <el-input
              v-model="queryParams.deptId"
              placeholder="请输入科室名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="项目名称" prop="healthItemId">
            <el-input
              v-model="queryParams.healthItemId"
              placeholder="请输入项目名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="通知本人" prop="infoInform">
            <el-select v-model="queryParams.infoInform" placeholder="请选择通知本人" clearable size="small">
              <el-option
                v-for="dict in infoInformOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="单位名称" prop="infoDept">
            <el-input
              v-model="queryParams.infoDept"
              placeholder="请输入单位名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="体检医师" prop="infoDoctor">
            <el-input
              v-model="queryParams.infoDoctor"
              placeholder="请输入体检医师"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-date-picker
              v-model="daterangeCreateTime"
              size="small"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="更新时间">
            <el-date-picker
              v-model="daterangeUpdateTime"
              size="small"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
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
              v-hasPermi="['health:info:add']"
            >新增
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['health:info:edit']"
            >修改
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['health:info:remove']"
            >删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['health:info:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="编号" align="center" prop="infoId"/>
          <el-table-column label="体检号" align="center" prop="infoCode"/>
          <el-table-column label="身份证号" align="center" prop="infoCardno"/>
          <el-table-column label="姓名" align="center" prop="infoName"/>
          <el-table-column label="年龄" align="center" prop="infoAge" />
          <el-table-column label="性别" align="center" prop="infoSex" :formatter="infoSexFormat"/>
          <el-table-column label="科室名称" align="center" prop="deptName"/>
          <el-table-column label="项目名称" align="center" prop="healthItemName"/>
          <el-table-column label="手机号" align="center" prop="infoPhone"/>
          <el-table-column label="通知本人" align="center" prop="infoInform" :formatter="infoInformFormat"/>
          <el-table-column label="单位名称" align="center" prop="infoDept"/>
          <el-table-column label="体检医师" align="center" prop="infoDoctor"/>
          <el-table-column label="阳性结果" align="center" prop="infoResult"/>
          <el-table-column label="诊治经过" align="center" prop="infoHospital"/>
          <el-table-column label="诊治结果" align="center" prop="infoConsequence"/>
          <el-table-column label="创建者" align="center" prop="createBy"/>
          <el-table-column label="创建时间" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="更新者" align="center" prop="updateBy"/>
          <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['health:info:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['health:info:remove']"
              >删除
              </el-button>
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
    <!-- 添加或修改重大阳性信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="体检号" prop="infoCode">
          <el-input v-model="form.infoCode" placeholder="请输入体检号"/>
        </el-form-item>
        <el-form-item label="身份号" prop="infoCardno">
          <el-input v-model="form.infoCardno" placeholder="请输入身份号"/>
        </el-form-item>
        <el-form-item label="姓名" prop="infoName">
          <el-input v-model="form.infoName" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="年龄" prop="infoAge">
          <el-input v-model="form.infoAge" placeholder="请输入年龄"/>
        </el-form-item>
        <el-form-item label="性别" prop="infoSex">
          <el-select v-model="form.infoSex" placeholder="请选择性别">
            <el-option
              v-for="dict in infoSexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科室名称" prop="deptId">
          <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择科室"/>
          <!--          <el-input v-model="form.deptId" placeholder="请输入科室名称" />-->
        </el-form-item>
        <el-form-item label="项目名称" prop="healthItemId">
          <el-select v-model="form.healthItemId" placeholder="请输入项目名称">
            <el-option
              v-for="healthItem in healthItemOptions"
              :key="healthItem.healthItemId"
              :label="healthItem.healthItemName"
              :value="healthItem.healthItemId"
            ></el-option>
          </el-select>
          <!--          <el-input v-model="form.healthItemId" placeholder="请输入项目名称" />-->
        </el-form-item>
        <el-form-item label="手机号" prop="infoPhone">
          <el-input v-model="form.infoPhone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item label="通知本人" prop="infoInform">
          <el-select v-model="form.infoInform" placeholder="请选择通知本人">
            <el-option
              v-for="dict in infoInformOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单位名称" prop="infoDept">
          <el-input v-model="form.infoDept" placeholder="请输入单位名称"/>
        </el-form-item>
        <el-form-item label="体检医师" prop="infoDoctor">
          <el-input v-model="form.infoDoctor" placeholder="请输入体检医师"/>
        </el-form-item>
        <el-form-item label="重大阳性结果" prop="infoResult">
          <el-input v-model="form.infoResult" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="诊治经过" prop="infoHospital">
          <el-input v-model="form.infoHospital" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="诊治结果" prop="infoConsequence">
          <el-input v-model="form.infoConsequence" type="textarea" placeholder="请输入内容"/>
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
import {listInfo, getInfo, delInfo, addInfo, updateInfo, exportInfo} from "@/api/health/info";
import {treeselect} from "@/api/system/dept";
import {listItem} from "@/api/health/item";
import {getUserProfile} from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";


export default {
  name: "Info",
  components: {Treeselect},
  data() {
    return {
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
      // 重大阳性信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //用户信息
      user:undefined,
      // 部门树选项
      deptOptions: undefined,

      // 部门名称
      deptName: undefined,
      // 项目树选项
      healthItemOptions: [],
      // 年龄字典
      infoAgeOptions: [],
      // 性别字典
      infoSexOptions: [],
      // 通知本人字典
      infoInformOptions: [],
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 更新时间时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        infoCode: null,
        infoCardno: null,
        infoName: null,
        infoAge: null,
        infoSex: null,
        deptId: null,
        healthItemId: null,
        infoInform: null,
        infoDept: null,
        infoDoctor: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
        infoCode: [
          {required: true, message: "体检号不能为空", trigger: "blur"}
        ],
        infoCardno: [
          {required: true, message: "身份号不能为空", trigger: "blur"}
        ],
        infoName: [
          {required: true, message: "姓名不能为空", trigger: "blur"}
        ],
        infoInform: [
          {required: true, message: "通知本人不能为空", trigger: "change"}
        ],
        infoDoctor: [
          {required: true, message: "体检医师不能为空", trigger: "blur"}
        ],
        infoResult: [
          {required: true, message: "重大阳性结果不能为空", trigger: "blur"}
        ],
        infoHospital: [
          {required: true, message: "诊治经过不能为空", trigger: "blur"}
        ],
        infoConsequence: [
          {required: true, message: "诊治结果不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getUser();
    this.getTreeselect();
    this.getTreeselectitme();
    this.getDicts("sys_user_sex").then(response => {
      this.infoAgeOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.infoSexOptions = response.data;
    });
    this.getDicts("health_notice_status").then(response => {
      this.infoInformOptions = response.data;
    });
  },
  watch: {
      // 根据名称筛选部门树
      deptName(val) {
        this.$refs.tree.filter(val);
      }
  },
  methods: {
    /** 查询部门下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    getUserInfo(){

    },
    getUser(){
      getUserProfile().then(response => {
        this.queryParams.deptId=response.data.deptId;
      });
    },


    /** 查询项目下拉结构 */
    getTreeselectitme() {
      listItem( this.queryParams.deptId).then(response => {
        this.healthItemOptions = response.rows;
        this.queryParams.deptId='';
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
      this.getList();
    },

    /** 查询重大阳性信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 年龄字典翻译
    infoAgeFormat(row, column) {
      return this.selectDictLabel(this.infoAgeOptions, row.infoAge);
    },
    // 性别字典翻译
    infoSexFormat(row, column) {
      return this.selectDictLabel(this.infoSexOptions, row.infoSex);
    },
    // 通知本人字典翻译
    infoInformFormat(row, column) {
      return this.selectDictLabel(this.infoInformOptions, row.infoInform);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        infoId: null,
        infoCode: null,
        infoCardno: null,
        infoName: null,
        infoAge: null,
        infoSex: null,
        deptId: null,
        healthItemId: null,
        infoPhone: null,
        infoInform: null,
        infoDept: null,
        infoDoctor: null,
        infoResult: null,
        infoHospital: null,
        infoConsequence: null,
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
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.infoId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加重大阳性信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const infoId = row.infoId || this.ids
      getInfo(infoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改重大阳性信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.infoId != null) {
            this.form.updateBy = this.$store.getters.name;
            updateInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.createBy = this.$store.getters.name;
            addInfo(this.form).then(response => {
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
      const infoIds = row.infoId || this.ids;
      this.$confirm('是否确认删除重大阳性信息编号为"' + infoIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delInfo(infoIds);
      }).then(() => {
        this.getList();
        con
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有重大阳性信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportInfo(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
