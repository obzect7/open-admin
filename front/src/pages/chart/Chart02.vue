<template>
  <div>
    <a-form>
      <a-row type="flex" style="background: white; padding: 20px;">
        <a-col :span="24" :xl="12" >
          <canvas id="doughnut01"></canvas>
        </a-col>
        <a-col :span="24" :xl="12">
          <canvas id="doughnut02"></canvas>
        </a-col>
      </a-row>
      <a-row type="flex" style="background: white; padding: 20px;">
        <a-col :span="24" :xl="12" >
          <canvas id="polarArea01"></canvas>
        </a-col>
        <a-col :span="24" :xl="12">
          <canvas id="polarArea02"></canvas>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>

<script>
import {Chart, registerables} from 'chart.js';
import {getChartList} from "@/services/chart";

Chart.register(...registerables);

export default {
  methods: {
    doughnutChart01() {
      const ctx = document.getElementById('doughnut01');

      //조회된 데이터 차트에 넣기
      const doughnut_labels = [];
      const doughnut_data01 = [];
      const doughnut_color =
          [   "#f7dcdc"
            , "#f2b3b3"
            , "#f58989"
            , "#de6a6a"
            , "#de5454"
            , "#e63535"
          ];

      for (var i = 0; i < this.chartList.length; i++) {
        doughnut_labels.push(this.chartList[i].grp_nm);
        doughnut_data01.push(this.chartList[i].data_01);

        if(i==5) break; // 12월은 너무 많음
      }

      const doughnutChart01 = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: doughnut_labels,
          datasets: [
            {
              backgroundColor: doughnut_color,
              data: doughnut_data01
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '라인01 분기별 소비량',
              font: {
                size: 20
              }
            }
          }
        },
      });
    },
    doughnutChart02() {
      const ctx = document.getElementById('doughnut02');

      //조회된 데이터 차트에 넣기
      const doughnut_labels = [];
      const doughnut_data01 = [];
      const doughnut_color = [];

      for (var i = 0; i < this.chartList.length; i++) {
        doughnut_labels.push(this.chartList[i].grp_nm);
        doughnut_data01.push(this.chartList[i].data_01);
        doughnut_color.push(this.chartList[i].color);

        if(i==5) break; // 12월은 너무 많음
      }

      const doughnutChart02 = new Chart(ctx, {
        type: 'pie',
        data: {
          labels: doughnut_labels,
          datasets: [
            {
              backgroundColor: doughnut_color,
              data: doughnut_data01
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '라일02 분기별 생산량',
              font: {
                size: 20
              }
            }
          }
        },
      });
    },
    polarAreaChar01() {
      const ctx = document.getElementById('polarArea01');
      console.log(this.chartList);
      //조회된 데이터 차트에 넣기
      const myChart_labels = [];
      const myChart_data01 = [];
      const myChart_data02 = [];
      const myChart_color = [];

      for (var i = 0; i < this.chartList.length; i++) {
        myChart_labels.push(this.chartList[i].grp_nm);
        myChart_data01.push(this.chartList[i].data_01);
        myChart_data01.push(this.chartList[i].data_02);
        myChart_color.push(this.chartList[i].color);

        if(i==5) break; // 12월은 너무 많음
      }

      const polarAreaChar01 = new Chart(ctx, {
        type: 'radar',
        data: {
          labels: myChart_labels,
          datasets: [
            {
              label: '소비량',
              backgroundColor: "#db3737",
              data: myChart_data01
            },
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '달별 소비량',
              font: {
                size: 20
              }
            }
          }
        },
      });
    },
    polarAreaChar02() {
      const ctx = document.getElementById('polarArea02');

      //조회된 데이터 차트에 넣기
      const myChart_labels = [];
      const myChart_data01 = [];
      const myChart_color = [];

      for (var i = 0; i < this.chartList.length; i++) {
        myChart_labels.push(this.chartList[i].grp_nm);
        myChart_data01.push(this.chartList[i].data_01);
        myChart_color.push(this.chartList[i].color);

        if(i==5) break; // 12월은 너무 많음
      }

      const polarAreaChar02 = new Chart(ctx, {
        type: 'polarArea',
        data: {
          labels: myChart_labels,
          datasets: [
            {
              backgroundColor: myChart_color,
              data: myChart_data01
            }
          ]
        },
        options: {
          responsive: true,
          scales: {
            r: {
              pointLabels: {
                display: true,
                centerPointLabels: true,
                font: {
                  size: 18
                }
              }
            }
          },
          plugins: {
            legend: {
              position: 'top',
            },
            title: {
              display: true,
              text: '달별 소비량',
              font: {
                size: 20
              }
            }
          }
        },
      });
    },
  },
  mounted() {
    getChartList().then(
        (res) => {
          if (res.data.length > 0) {
            this.chartList = res.data;

            this.doughnutChart01();
            this.doughnutChart02();
            this.polarAreaChar01();
            this.polarAreaChar02();
          }
        }
    );
  },
  data() {
    return {
      myChart: null,
      mobile_yn: '',
      chartList: '',
    };
  },
}
</script>

<style>
</style>