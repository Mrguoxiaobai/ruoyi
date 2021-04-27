<template>
  <div class="psy">
    <el-row>
      <el-col :span="6" v-for="(count,i) in this.pcountList ">
        <el-card class="box-card title">
          <div slot="header" class="clearfix">
            <span style="float: left;font-weight:bold">{{count.year}}</span>
            <el-tag
              style="float: right"
              size="mini"
              :color="count.initColor"
            ><span style="color:#ffffff;">体检人数</span>
            </el-tag>
          </div>
          <div class="text">
            <div style="float: left">
              <p class="count">{{count.pCount}}</p>
              <p style="font-size: small">体检率：{{count.pCentage}}%</p>

            </div>
            <div class="text1" style="float: right">
              <ve-liquidfill  :data="chartData3[i]"  :settings="chartSetting3[i]" width="100%" height="100%" />
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-card class="box-card histogram">
          <div slot="header" class="clearfix">
            <el-tag
              style="float: left"
              size="mini"
              color="red"
            >
              <span style="color:#ffffff;">三类人员统计表</span>
            </el-tag>
          </div>
          <div>
            <ve-histogram
              height="380px"
              :data="chartData"
              :extend="{
                legend: {show: true, x:'center', y: 20, itemGap: 20, orient: 'horizontal', align:'left' },
                color: ['#409EFF','#67C23A','#F56C6C'],
                series:{
                  itemStyle:{barBorderRadius:[10,10,5,5,]}
                }
              }"
              :settings="chartSettings"
            />
          </div>
        </el-card>
      </el-col>
      <el-col :span="24">
        <el-card class="box-card histogram">
          <div slot="header" class="clearfix">
            <el-tag
              style="float: left"
              size="mini"
              color="#f5616f"
            >
              <span style="color:#ffffff;">体检异常统计表</span>
            </el-tag>
          </div>
          <div>
            <ve-histogram
              height="380px"
              :data="chartData1"
              :extend="{
                legend: {show: true, x:'center', y: 20, itemGap: 20, orient: 'horizontal', align:'left' },
                color: ['#50c48f','#26ccd8','#3685fe','#9977ef','#f5616f','#f7b13f','#f47a75','#009db2','#67C23A'],
                series:{
                  itemStyle:{barBorderRadius:[15,15,5,5,]}
                }
              }"
              :settings="chartSettings1"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {listPcount} from "@/api/ecg/pcount";
import {listThreeType} from "@/api/ecg/threeType";
import {listPositive} from "@/api/ecg/positive";

export default {
  name: 'Index',
  components: {
  },
  data() {
    return {
      pcountList:[],
      threeTypeList:[],
      positiveList:[],
      chartData: {
        columns: ['日期', '一类', '二类','三类'],
        rows: []
      },
      chartSettings: {
        metrics: ['一类', '二类','三类'],
        dimension: ['日期']
      },
      chartData1:{
        columns: ['年份','超重',	'高血压','甘油三酯增高','总胆固醇增高','高血糖','低密度脂蛋白增高','谷氨酰转酞酶增高','谷丙转氨酶增高',	'脂肪肝（轻度）'],
        rows: []
      },
      chartSettings1: {
        metrics: ['超重',	'高血压','甘油三酯增高','总胆固醇增高','高血糖','低密度脂蛋白增高','谷氨酰转酞酶增高','谷丙转氨酶增高',	'脂肪肝（轻度）'],
        dimension: ['年份']
      },
      chartData3:[],
      chartSetting3:[]
    }
  },
  methods: {
    getColor(vaule){
      var init=['#50c48f','#26ccd8','#3685fe','#9977ef','#f5616f','#f7b13f','#f47a75','#009db2','#024b51','#0780cf','#409EFF','#606266','#67C23A','#E6A23C','#000000','#F56C6C','#909399','#303133','#FF0000','#40E0D0']
      if(vaule==null&&vaule>init.length){
        return init[Math.floor((Math.random()*init.length))]
      }
     return init[vaule]
    },
    /** 查询历年体检数据列表 */
    getList() {
      listPcount(this.queryParams).then(response => {
        this.pcountList = response.rows;
        for (let i = 0; i < this.pcountList.length; i++) {
          this.pcountList[i].initColor=this.getColor(i);
          this.chartData3[i]={ columns:['title','percent'],
            rows:[{
              title:'体检率',
              percent: (this.pcountList[i].pCentage/100)-0.1
            }
            ]};
          this.chartSetting3[i]={
            seriesMap:{
              体检率:{
                radius:'90%',
                label:{
                  normal:{
                    formatter:(v)=>{
                      return `${Math.floor((v.data.value*100)+10)}%`
                    },
                    textStyle:{
                      fontSize:24,
                      color:'#999',
                      fontWeight:'normal',
                    },
                    position:['50%','55%'],
                    insideColor:'#fff'
                  }
                },
                //外边框属性
                outline:{
                  itemStyle:{
                    borderColor:'#aaa4a4',
                    borderWidth:1,
                    color: 'none',
                    shadowBlur:0,
                    shadowColor:'#fff'
                  },
                  borderDistance:0
                },
                backgroundStyle:{
                  color:'#fff'
                },
                itemStyle: {
                  shadowBlur: 0,
                  shadowColor: '#fff'
                },
                amplitude:8,
                color:[this.getColor(i)]
              }
            }
          }
        }
      });

      listThreeType(this.queryParams).then(response => {
        this.threeTypeList = response.rows;
        for (let i = 0; i < this.threeTypeList.length; i++) {
            let threeType=this.threeTypeList[i]
            this.chartData.rows[i]={
              '日期': threeType.year+'年', '一类': threeType.one, '二类': threeType.two,'三类':threeType.three
            }
        }
      });

      listPositive(this.queryParams).then(response => {
        this.positiveList = response.rows;
        for (let i = 0; i <this.positiveList.length ; i++) {
          let positive=this.positiveList[i];
          this.chartData1.rows[i]={
          '年份':positive.year,'超重':positive.overweight,'高血压':positive.hypertension,'甘油三酯增高':positive.tg,'总胆固醇增高':positive.tc,'高血糖':positive.glu,'低密度脂蛋白增高':positive.ldlC,'谷氨酰转酞酶增高':positive.ggt,'谷丙转氨酶增高':positive.alt,'脂肪肝（轻度）':positive.fattyLive
          }

        }
      });
    },
  },
  watch:{
  },
  mounted() {
    this.getList()
  }
}
</script>

<style lang="scss" scoped>
.psy{
  padding: 0 15px 0 15px;
}
.title{
  color: #666;
  height: 180px;
  margin: 10px 10px 10px 10px;
}
.histogram{
  color: #666;
  margin: 10px 10px 10px 10px;
}
.clearfix{
  font-size: 16px;
}
.count{
  font-size: 26px;
}
.text{
  padding: 0 ;
}
.text1{
  padding: 0;
}
</style>
