<template>
  <div>
    <a-form>
      <a-row type="flex" style="background: white; padding: 20px;">
        <a-col :span="24" :xl="12">
          <canvas id="bar"></canvas>
        </a-col>
        <a-col :span="24" :xl="12">
          <canvas id="line"></canvas>
        </a-col>
      </a-row>
      <a-row type="flex" style="background: white; padding: 20px;">
        <a-col :span="24" :xl="12" >
          <canvas id="doughnut"></canvas>
        </a-col>
        <a-col :span="24" :xl="12">
          <canvas id="polarArea"></canvas>
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
    barChart() {
      const ctx = document.getElementById('bar');

      //조회된 데이터 차트에 넣기
      const bar_labels = [];
      const bar_data01 = [];
      const bar_data02 = [];

      for (var i = 0; i < this.chartList.length; i++) {
        bar_labels.push(this.chartList[i].grp_nm);
        bar_data01.push(this.chartList[i].data_01);
        bar_data02.push(this.chartList[i].data_02);
      }

      this.myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: bar_labels,
          datasets: [
            {
              label: "일광산업",
              backgroundColor: "#f87979",
              data: bar_data01
            },
            {
              label: "주박산업",
              backgroundColor: "#79f899",
              data: bar_data02
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '자재현황',
              font: {
                size: 20
              }
            }
          }
        },
      });
    },
    lineChart() {
      const ctx = document.getElementById('line');

      //조회된 데이터 차트에 넣기
      const line_labels = [];
      const line_data01 = [];
      const line_data02 = [];

      for (var i = 0; i < this.chartList.length; i++) {
        line_labels.push(this.chartList[i].grp_nm);
        line_data01.push(this.chartList[i].data_01);
        line_data02.push(this.chartList[i].data_02);
      }

      this.myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: line_labels,
          datasets: [
            {
              label: "일광산업",
              backgroundColor: "#f87979",
              data: line_data01
            },
            {
              label: "주박산업",
              backgroundColor: "#79f899",
              data: line_data02
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '인력현황',
              font: {
                size: 20
              }
            }
          },
        },
      });
    },
    doughnutChart() {
      const ctx = document.getElementById('doughnut');

      //조회된 데이터 차트에 넣기
      const doughnut_labels = [];
      const doughnut_data01 = [];
      const doughnut_color = [];

      for (var i = 0; i < this.chartList.length; i++) {
        doughnut_labels.push(this.chartList[i].grp_nm);
        doughnut_data01.push(this.chartList[i].data_01);
        doughnut_color.push(this.chartList[i].color);
      }

      this.myChart = new Chart(ctx, {
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
              text: '달별 생산량',
              font: {
                size: 20
              }
            }
          }
        },
      });
    },
    polarAreaChart() {
      const ctx = document.getElementById('polarArea');

      //조회된 데이터 차트에 넣기
      const myChart_labels = [];
      const myChart_data01 = [];
      const myChart_color = [];

      for (var i = 0; i < this.chartList.length; i++) {
        myChart_labels.push(this.chartList[i].grp_nm);
        myChart_data01.push(this.chartList[i].data_01);
        myChart_color.push(this.chartList[i].color);
      }

      this.myChart = new Chart(ctx, {
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
  },
  mounted() {
    getChartList().then(
        (res) => {
          if (res.data.length > 0) {
            this.chartList = res.data;

            this.barChart();
            this.lineChart();
            this.doughnutChart();
            this.polarAreaChart();
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