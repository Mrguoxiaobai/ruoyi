<template>
<div class="psy">
  <el-row>
      <el-col :span="24">
        <el-card class="box-card title">
          <div slot="header" class="clearfix">
            <span class="cl" >心理测试</span>
          </div>
          <h5 class="cl1" >&nbsp&nbsp&nbsp 测试指导语：根据该问题与您自己的实际情况相符合的程度（最近一个星期或现在）选择一个适当的答案。</h5>
        </el-card>
      </el-col>
    <el-col :span="24">
      <div class="input">
        <el-input  size="medium"  v-model="idcardno" placeholder="请输入身份证号"></el-input>
      </div>
      <el-card class="box-card body" v-for="(subjet,i) in subjetList" >
          <div slot="header" class="clearfix1">
            <el-button type="primary" plain size="500px">{{i+1}}、{{subjet.subjetName}}</el-button>
          </div>
        <el-radio-group v-if="i!=46" v-model="score[i+1]" >
          <el-radio :label="1">{{subjet.subjetA}}</el-radio>
          <el-radio :label="2">{{subjet.subjetB}}</el-radio>
          <el-radio :label="3">{{subjet.subjetC}}</el-radio>
          <el-radio :label="4">{{subjet.subjetD}}</el-radio>
          <el-radio :label="5">{{subjet.subjetE}}</el-radio>
        </el-radio-group>
        <el-radio-group v-if="i==46" v-model="score[i+1]" >
          <el-radio :label="5">{{subjet.subjetA}}</el-radio>
          <el-radio :label="4">{{subjet.subjetB}}</el-radio>
          <el-radio :label="3">{{subjet.subjetC}}</el-radio>
          <el-radio :label="2">{{subjet.subjetD}}</el-radio>
          <el-radio :label="1">{{subjet.subjetE}}</el-radio>
        </el-radio-group>
      </el-card>
    </el-col>
    <el-col :span="24" >
      <el-card class="box-card submit">
        <el-button type="primary" @click="calculate()">完成答题</el-button>
      </el-card>

    <!--弹出的提示框-->
      <el-dialog class="dialog" title="评测结果" :visible.sync="dialogVisible" :before-close="handleClose">
        <el-card class="box-card">
          <span class="cl1">此生理心理解读，仅针对本次答题</span>
          <el-divider></el-divider>
          <span class="cl1">身份证号：{{idcardno}}</span>
          <el-divider direction="vertical"></el-divider>
          <el-divider direction="vertical"></el-divider>
          <span class="cl1">总分：{{this.subjetScore.score}}</span>
          <el-divider direction="vertical"></el-divider>
          <span class="cl1">症状：{{this.subjetScore.genreName}}</span>
          <el-divider></el-divider>
          <div class="block" >
            <el-timeline >
              <el-timeline-item color="#409EFF" timestamp="身体疲劳" placement="top">
                <el-card>
                  <h4>{{this.subjetScore.genre1Name}}</h4>
                  <el-divider></el-divider>
                  <p>{{this.subjetScore.genre1des}}</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item color="#409EFF" timestamp="关系敏感" placement="top">
                <el-card>
                  <h4>{{this.subjetScore.genre2Name}}</h4>
                  <el-divider></el-divider>
                  <p>{{this.subjetScore.genre2des}}</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item color="#409EFF" timestamp="郁闷程度" placement="top">
                <el-card>
                  <h4>{{this.subjetScore.genre3Name}}</h4>
                  <el-divider></el-divider>
                  <p>{{this.subjetScore.genre3des}}</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item color="#409EFF" timestamp="焦虑程度" placement="top">
                <el-card>
                  <h4>{{this.subjetScore.genre4Name}}</h4>
                  <el-divider></el-divider>
                  <p>{{this.subjetScore.genre4des}}</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item color="#409EFF" timestamp="紧张反应" placement="top">
                <el-card>
                  <h4>{{this.subjetScore.genre5Name}}</h4>
                  <el-divider></el-divider>
                  <p>{{this.subjetScore.genre5des}}</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item color="#409EFF" timestamp="工作-家庭冲突" placement="top">
                <el-card>
                  <h4>{{this.subjetScore.genre6Name}}</h4>
                  <el-divider></el-divider>
                  <p>{{this.subjetScore.genre6des}}</p>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </div>
        </el-card>

        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>

    </el-col>
  </el-row>
</div>
</template>

<script>
import {listSubjet} from "@/api/psy/subjet";
import {listGenre} from "@/api/psy/genre";
import {addScore} from "@/api/psy/score";
export default {
  name: "Psy",
  data() {
    return {
      dialogVisible: false,
      // 题目信息表格数据
      subjetList: [],
      genreList:[],
      idcardno:'',
      //答案数据
      score:[],
      // 查询参数
      queryParams: {
        subjetGroup: '心理评估',
      },
      subjetScore : {
        scoreId: null,
        patientcode: null,
        idcardno: null,
        patientname: null,
        inputCode: null,
        orgName: null,
        orgDepart: null,
        sex: null,
        age: null,
        marriage: null,
        nation: null,
        phone: null,
        education: null,
        occupation: null,
        score: null,
        genre1Score: null,
        genre2Score: null,
        genre3Score: null,
        genre4Score: null,
        genre5Score: null,
        genre6Score: null,
        genre1: null,
        genre2: null,
        genre3: null,
        genre4: null,
        genre5: null,
        genre6: null,
        genreName: null,
        genre1Name: null,
        genre2Name: null,
        genre3Name: null,
        genre4Name: null,
        genre5Name: null,
        genre6Name: null,
        genre1des: null,
        genre2des: null,
        genre3des: null,
        genre4des: null,
        genre5des: null,
        genre6des: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        subjet1: null,
        subjet2: null,
        subjet3: null,
        subjet4: null,
        subjet5: null,
        subjet6: null,
        subjet7: null,
        subjet8: null,
        subjet9: null,
        subjet10: null,
        subjet11: null,
        subjet12: null,
        subjet13: null,
        subjet14: null,
        subjet15: null,
        subjet16: null,
        subjet17: null,
        subjet18: null,
        subjet19: null,
        subjet20: null,
        subjet21: null,
        subjet22: null,
        subjet23: null,
        subjet24: null,
        subjet25: null,
        subjet26: null,
        subjet27: null,
        subjet28: null,
        subjet29: null,
        subjet30: null,
        subjet31: null,
        subjet32: null,
        subjet33: null,
        subjet34: null,
        subjet35: null,
        subjet36: null,
        subjet37: null,
        subjet38: null,
        subjet39: null,
        subjet40: null,
        subjet41: null,
        subjet42: null,
        subjet43: null,
        subjet44: null,
        subjet45: null,
        subjet46: null,
        subjet47: null,
        subjet48: null,
        subjet49: null,
        subjet50: null
      }
    };
  },

  created() {
    this.getList();
    listGenre(this.queryParams).then(response => {
      this.genreList = response.rows;
      console.log(this.genreList)
    });
  },
  methods: {
    //重置数据
    reset() {
      this.subjetScore={
        idcardno: null,
        score: null,
        genre1Score: null,
        genre2Score: null,
        genre3Score: null,
        genre4Score: null,
        genre5Score: null,
        genre6Score: null,
        genre1: null,
        genre2: null,
        genre3: null,
        genre4: null,
        genre5: null,
        genre6: null,
        genreName: null,
        genre1Name: null,
        genre2Name: null,
        genre3Name: null,
        genre4Name: null,
        genre5Name: null,
        genre6Name: null,
        genre1des: null,
        genre2des: null,
        genre3des: null,
        genre4des: null,
        genre5des: null,
        genre6des: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        subjet1: null,
        subjet2: null,
        subjet3: null,
        subjet4: null,
        subjet5: null,
        subjet6: null,
        subjet7: null,
        subjet8: null,
        subjet9: null,
        subjet10: null,
        subjet11: null,
        subjet12: null,
        subjet13: null,
        subjet14: null,
        subjet15: null,
        subjet16: null,
        subjet17: null,
        subjet18: null,
        subjet19: null,
        subjet20: null,
        subjet21: null,
        subjet22: null,
        subjet23: null,
        subjet24: null,
        subjet25: null,
        subjet26: null,
        subjet27: null,
        subjet28: null,
        subjet29: null,
        subjet30: null,
        subjet31: null,
        subjet32: null,
        subjet33: null,
        subjet34: null,
        subjet35: null,
        subjet36: null,
        subjet37: null,
        subjet38: null,
        subjet39: null,
        subjet40: null,
        subjet41: null,
        subjet42: null,
        subjet43: null,
        subjet44: null,
        subjet45: null,
        subjet46: null,
        subjet47: null,
        subjet48: null,
        subjet49: null,
        subjet50: null
      }
    },
    /** 查询题目信息列表 */
    getList() {
      this.loading = true;
      listSubjet(this.queryParams).then(response => {
        this.subjetList = response.rows;
        this.loading = false;
      });
    },


    //计算答题分值
    calculate(){
      var flag=true;
      if(this.idcardno==''){
        flag=false;
        this.msgError("请输入身份证号！！");
      } else if(this.score.length==0){
        flag=false;
        this.msgError("请选择答案");
      }else {
        for(var i=1;i<=50;i++){
          if(!this.score[i]){
            flag=false;
            this.msgError("第"+i+"没有选择答案！！");
            break;
          }
        }
      }

      if(flag){
        this.subjetScore.idcardno=this.idcardno
        this.subjetScore.subjet1=this.score[1]
        this.subjetScore.subjet2=this.score[2]
        this.subjetScore.subjet3=this.score[3]
        this.subjetScore.subjet4=this.score[4]
        this.subjetScore.subjet5=this.score[5]
        this.subjetScore.subjet6=this.score[6]
        this.subjetScore.subjet7=this.score[7]
        this.subjetScore.subjet8=this.score[8]
        this.subjetScore.subjet9=this.score[9]
        this.subjetScore.subjet10=this.score[10]
        this.subjetScore.subjet11=this.score[11]
        this.subjetScore.subjet12=this.score[12]
        this.subjetScore.subjet13=this.score[13]
        this.subjetScore.subjet14=this.score[14]
        this.subjetScore.subjet15=this.score[15]
        this.subjetScore.subjet16=this.score[16]
        this.subjetScore.subjet17=this.score[17]
        this.subjetScore.subjet18=this.score[18]
        this.subjetScore.subjet19=this.score[19]
        this.subjetScore.subjet20=this.score[20]
        this.subjetScore.subjet21=this.score[21]
        this.subjetScore.subjet22=this.score[22]
        this.subjetScore.subjet23=this.score[23]
        this.subjetScore.subjet24=this.score[24]
        this.subjetScore.subjet25=this.score[25]
        this.subjetScore.subjet26=this.score[26]
        this.subjetScore.subjet27=this.score[27]
        this.subjetScore.subjet28=this.score[28]
        this.subjetScore.subjet29=this.score[29]
        this.subjetScore.subjet30=this.score[30]
        this.subjetScore.subjet31=this.score[31]
        this.subjetScore.subjet32=this.score[32]
        this.subjetScore.subjet33=this.score[33]
        this.subjetScore.subjet34=this.score[34]
        this.subjetScore.subjet35=this.score[35]
        this.subjetScore.subjet36=this.score[36]
        this.subjetScore.subjet37=this.score[37]
        this.subjetScore.subjet38=this.score[38]
        this.subjetScore.subjet39=this.score[39]
        this.subjetScore.subjet40=this.score[40]
        this.subjetScore.subjet41=this.score[41]
        this.subjetScore.subjet42=this.score[42]
        this.subjetScore.subjet43=this.score[43]
        this.subjetScore.subjet44=this.score[44]
        this.subjetScore.subjet45=this.score[45]
        this.subjetScore.subjet46=this.score[46]
        this.subjetScore.subjet47=this.score[47]
        this.subjetScore.subjet48=this.score[48]
        this.subjetScore.subjet49=this.score[49]
        this.subjetScore.subjet50=this.score[50]
        //计算总分级因子分
        var sum=0;
        var sum1=0;
        var sum2=0;
        var sum3=0;
        var sum4=0;
        var sum5=0;
        var sum6=0;

        this.score.map(function (value, i, array){
          sum += value;
          if(i<=12){
            sum1 +=value;
          }else if(i<=21){
            sum2 +=value;
          } else if(i<=34){
            sum3 +=value;
          }else if(i<=44){
            sum4 +=value;
          }else if(i<=47){
            sum5 +=value;
          }else if(i<=50){
            sum6 +=value;
          }

        })
        this.subjetScore.score=sum;
        this.subjetScore.genre1Score=sum1;
        this.subjetScore.genre2Score=sum2;
        this.subjetScore.genre3Score=sum3;
        this.subjetScore.genre4Score=sum4;
        this.subjetScore.genre5Score=sum5;
        this.subjetScore.genre6Score=sum6;
        //计算总症状 及因子症状
        var scoreGenre =this.subjetScore.score/50.00;
        this.subjetScore.genre1 =sum1/12.0;
        this.subjetScore.genre2 =sum2/9.0;
        this.subjetScore.genre3 =sum3/13.0;
        this.subjetScore.genre4 =sum4/10.0;
        this.subjetScore.genre5 =sum5/3.0;
        this.subjetScore.genre6 =sum6/3.0;
        if (scoreGenre>=3.26){
          this.subjetScore.genreName ='五级'
        }else if(scoreGenre>=2.45){
          this.subjetScore.genreName ='四级'
        }else if(scoreGenre>1.95){
          this.subjetScore.genreName ='三级'
        }else if(scoreGenre>=1.47){
          this.subjetScore.genreName ='二级'
        }else if(scoreGenre>0){
          this.subjetScore.genreName ='一级'
        }
        //1.身体疲劳
        if(this.subjetScore.genre1>=3.48){
          this.subjetScore.genre1Name = '五级'
          this.subjetScore.genre1des=this.genreList[4].genreDes
        }else if(this.subjetScore.genre1>=2.63){
          this.subjetScore.genre1Name = '四级'
          this.subjetScore.genre1des=this.genreList[3].genreDes
        }else if(this.subjetScore.genre1>=1.78){
          this.subjetScore.genre1Name = '三级'
          this.subjetScore.genre1des=this.genreList[2].genreDes
        }else if(this.subjetScore.genre1>=1.10){
          this.subjetScore.genre1Name = '二级'
          this.subjetScore.genre1des=this.genreList[1].genreDes
        }else if(this.subjetScore.genre1Score>0){
          this.subjetScore.genre1Name = '一级'
          this.subjetScore.genre1des=this.genreList[0].genreDes
        }
        //2.关系敏感
        if(this.subjetScore.genre2>=3.33){
          this.subjetScore.genre2Name = '五级'
          this.subjetScore.genre2des=this.genreList[9].genreDes
        }else if(this.subjetScore.genre2>=2.51){
          this.subjetScore.genre2Name = '四级'
          this.subjetScore.genre2des=this.genreList[8].genreDes
        }else if(this.subjetScore.genre2>=1.68){
          this.subjetScore.genre2Name = '三级'
          this.subjetScore.genre2des=this.genreList[7].genreDes
        }else if(this.subjetScore.genre2>=1.05){
          this.subjetScore.genre2Name = '二级'
          this.subjetScore.genre2des=this.genreList[6].genreDes
        }else if(this.subjetScore.genre2Score>0){
          this.subjetScore.genre2Name = '一级'
          this.subjetScore.genre2des=this.genreList[5].genreDes
        }

        //3.郁闷程度
        if(this.subjetScore.genre3>=3.42){
          this.subjetScore.genre3Name = '五级'
          this.subjetScore.genre3des=this.genreList[14].genreDes
        }else if(this.subjetScore.genre3>=2.55){
          this.subjetScore.genre3Name = '四级'
          this.subjetScore.genre3des=this.genreList[13].genreDes
        }else if(this.subjetScore.genre3>=1.77){
          this.subjetScore.genre3Name = '三级'
          this.subjetScore.genre3des=this.genreList[12].genreDes
        }else if(this.subjetScore.genre3>=1.08){
          this.subjetScore.genre3Name = '二级'
          this.subjetScore.genre3des=this.genreList[11].genreDes
        }else if(this.subjetScore.genre3Score>0){
          this.subjetScore.genre3Name = '一级'
          this.subjetScore.genre3des=this.genreList[10].genreDes
        }
        //4.焦虑程度
        if(this.subjetScore.genre4>=3.26){
          this.subjetScore.genre4Name = '五级'
          this.subjetScore.genre4des=this.genreList[19].genreDes
        }else if(this.subjetScore.genre4>=2.37){
          this.subjetScore.genre4Name = '四级'
          this.subjetScore.genre4des=this.genreList[18].genreDes
        }else if(this.subjetScore.genre4>=1.51){
          this.subjetScore.genre4Name = '三级'
          this.subjetScore.genre4des=this.genreList[17].genreDes
        }else if(this.subjetScore.genre4>=1.04){
          this.subjetScore.genre4Name = '二级'
          this.subjetScore.genre4des=this.genreList[16].genreDes
        }else if(this.subjetScore.genre4Score>0){
          this.subjetScore.genre4Name = '一级'
          this.subjetScore.genre4des=this.genreList[15].genreDes
        }

        //5.紧张反应
        if(this.subjetScore.genre5>=3.12){
          this.subjetScore.genre5Name = '五级'
          this.subjetScore.genre5des=this.genreList[24].genreDes
        }else if(this.subjetScore.genre5>=2.41){
          this.subjetScore.genre5Name = '四级'
          this.subjetScore.genre5des=this.genreList[23].genreDes
        }else if(this.subjetScore.genre5>=1.99){
          this.subjetScore.genre5Name = '三级'
          this.subjetScore.genre5des=this.genreList[22].genreDes
        }else if(this.subjetScore.genre5>=1.57){
          this.subjetScore.genre5Name = '二级'
          this.subjetScore.genre5des=this.genreList[21].genreDes
        }else if(this.subjetScore.genre5Score>0){
          this.subjetScore.genre5Name = '一级'
          this.subjetScore.genre5des=this.genreList[20].genreDes
        }
        //6.郁闷程度
        if(this.subjetScore.genre6>=4.16){
          this.subjetScore.genre6Name = '五级'
          this.subjetScore.genre6des=this.genreList[29].genreDes
        }else if(this.subjetScore.genre6>=3.43){
          this.subjetScore.genre6Name = '四级'
          this.subjetScore.genre6des=this.genreList[28].genreDes
        }else if(this.subjetScore.genre6>=2.89){
          this.subjetScore.genre6Name = '三级'
          this.subjetScore.genre6des=this.genreList[27].genreDes
        }else if(this.subjetScore.genre6>=2.17){
          this.subjetScore.genre6Name = '二级'
          this.subjetScore.genre6des=this.genreList[26].genreDes
        }else if(this.subjetScore.genre6Score>0){
          this.subjetScore.genre6Name = '一级'
          this.subjetScore.genre6des=this.genreList[25].genreDes
        }
        this.dialogVisible = true
      }
    },
    //完成答题
    submit(){
      addScore(this.subjetScore).then(response => {
        this.msgSuccess("答题成功");
        this.reset();
        this.score=[];
        this.dialogVisible = false;
      });
      console.log(this.subjetScore)
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },

  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.psy{
  //background-image: url("../../assets/images/bj.png");
  background: #FFFFFF;
  background-attachment:fixed;

}
.block{
  text-align: left;
}

.dialog{
  text-align: center;
  padding: 0px 0px 0px 0px;
}
.title {
  color: #409EFF;
  text-align: center;
  margin: 20px auto 20px auto;
  width: 800px;
}
.body{
  text-align: center;
  margin: 10px auto 0px auto;
  width: 680px;
}
.submit{
  text-align: right;
  margin: 0px auto 0px auto;
  width: 680px;
  padding: 0px 50px 0px 0px;
  height: 60px;
}
.input{
  color: #409EFF;
  margin: 0px auto 0px auto ;
  width: 380px;
}
.cl{
  text-align: center;
  font-size: xx-large;
  font-family: Serif;
}
.cl1{
  text-align: center;
  font-size: large;
  font-family: Serif;
}
.clearfix1{
  text-align: left;
}

</style>
