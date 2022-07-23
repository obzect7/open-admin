<template>
  <div>
    <a-form>
      <a-row type="flex" style="background: white; padding: 20px;">
        <a-col :span="24" :xl="12">
          <canvas id="bar01"></canvas>
        </a-col>
        <a-col :span="24" :xl="12">
          <canvas id="bar02"></canvas>
        </a-col>
      </a-row>
      <a-row type="flex" style="background: white; padding: 20px;">
        <a-col :span="24" :xl="12">
          <canvas id="line01"></canvas>
        </a-col>
        <a-col :span="24" :xl="12">
          <canvas id="line02"></canvas>
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
    barChart01() {
      const ctx = document.getElementById('bar01');

      //조회된 데이터 차트에 넣기
      const bar_labels = [];
      const bar_data01 = [];
      const bar_data02 = [];

      for (var i = 0; i < this.chartList.length; i++) {
        bar_labels.push(this.chartList[i].grp_nm);
        bar_data01.push(this.chartList[i].data_01);
        bar_data02.push(this.chartList[i].data_02);
      }

      const barChart01 = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: bar_labels,
          datasets: [
            {
              label: "CNC01",
              backgroundColor: "#3dc5cc",
              data: bar_data01
            },
            {
              label: "CNC02",
              backgroundColor: "#2130d1",
              data: bar_data02
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '라인1 자재현황',
              font: {
                size: 20
              }
            }
          },
          responsive: true,
          interaction: {
            intersect: false,
          },
          scales: {
            x: {
              stacked: true,
            },
            y: {
              stacked: true
            }
          }
        },
      });
    },
    barChart02() {
      const ctx = document.getElementById('bar02');

      //조회된 데이터 차트에 넣기
      const bar_labels = [];
      const bar_data01 = [];
      const bar_data02 = [];

      for (var i = 0; i < this.chartList.length; i++) {
        bar_labels.push(this.chartList[i].grp_nm);
        bar_data01.push(this.chartList[i].data_01);
        bar_data02.push(this.chartList[i].data_02);
      }

      const barChart02 = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: bar_labels,
          datasets: [
            {
              label: "CNC01",
              backgroundColor: "#b83ba7",
              data: bar_data01
            },
            {
              label: "CNC02",
              backgroundColor: "#ff0099",
              data: bar_data02
            }
          ]
        },
        options: {
          indexAxis: 'y',
          elements: {
            bar: {
              borderWidth: 2,
            }
          },
          responsive: true,
          plugins: {
            title: {
              display: true,
              text: '라인2 자재현황',
              font: {
                size: 20
              }
            },
            legend: {
              position: 'right',
            },
          },
        },
      });
    },
    lineChart01() {
      const ctx = document.getElementById('line01');

      //조회된 데이터 차트에 넣기
      const line_labels = [];
      const line_data01 = [];
      const line_data02 = [];

      for (var i = 0; i < this.chartList.length; i++) {
        line_labels.push(this.chartList[i].grp_nm);
        line_data01.push(this.chartList[i].data_01);
        line_data02.push(this.chartList[i].data_02);
      }

      const lineChart01 = new Chart(ctx, {
        type: 'line',
        data: {
          labels: line_labels,
          datasets: [
            {
              label: "CNC01",
              backgroundColor: "#ff6f00",
              data: line_data01
            },
            {
              label: "CNC02",
              backgroundColor: "#ffcd45",
              data: line_data02
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: '라인3 자재현황',
              font: {
                size: 20
              }
            }
          },
          animations: {
            tension: {
              duration: 1000,
              easing: 'linear',
              from: 1,
              to: 0,
              loop: true
            }
          },
        },
      });
    },
    lineChart02() {
      const ctx = document.getElementById('line02');

      //조회된 데이터 차트에 넣기
      const line_labels = [];
      const line_data01 = [];
      const line_data02 = [];

      for (var i = 0; i < this.chartList.length; i++) {
        line_labels.push(this.chartList[i].grp_nm);
        line_data01.push(this.chartList[i].data_01);
        line_data02.push(this.chartList[i].data_02);
      }

      const lineChart02 = new Chart(ctx, {
        type: 'line',
        data: {
          labels: line_labels,
          datasets: [
            {
              label: "CNC01",
              backgroundColor: "#ff6f00",
              borderColor: "#ff6f00",
              pointStyle: 'circle',
              pointRadius: 10,
              pointHoverRadius: 15,
              data: line_data01
            },
            {
              label: "CNC02",
              backgroundColor: "#ffcd45",
              borderColor : "#ffcd45",
              pointStyle: 'circle',
              pointRadius: 10,
              pointHoverRadius: 15,
              data: line_data02
            }
          ]
        },
        options: {
          plugins: {
            responsive: true,
            title: {
              display: true,
              text: '라인4 자재현황',
              font: {
                size: 20
              }
            }
          },
        },
      });
    },
  },
  mounted() {
    getChartList().then(
        (res) => {
          if (res.data.length > 0) {
            this.chartList = res.data;

            this.barChart01();
            this.barChart02();
            this.lineChart01();
            this.lineChart02();
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