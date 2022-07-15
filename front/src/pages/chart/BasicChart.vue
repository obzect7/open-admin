<template>
  <div>
    <a-form >
      <a-row type="flex"  justify="space-around">
        <a-col :span="11" style="background: white; padding: 20px;">
          <canvas id="bar"></canvas>
        </a-col>
        <a-col :span="11" style="background: white; padding: 20px;">
          <canvas id="line"></canvas>
        </a-col>
      </a-row>
      <a-row type="flex"  justify="space-around" style="padding-top: 10px">
        <a-col :span="11" style="background: white; padding: 20px;">
          <canvas id="doughnut"></canvas>
        </a-col>
        <a-col :span="11" style="background: white; padding: 20px;">
          <canvas id="polarArea"></canvas>
        </a-col>
      </a-row>
    </a-form>

  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default {
  methods:{
    barChart() {
      const ctx = document.getElementById('bar');
      this.myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "Data 01",
              backgroundColor: "#f87979",
              data: this.data01
            },
            {
              label: "Data 02",
              backgroundColor: "#79f899",
              data: this.data02
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: 'Bar Chart',
              font: {
                size: 20
              }
            }
          }
        }
      });
    },
    lineChart() {
      const ctx = document.getElementById('line');
      this.myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "Data 01",
              backgroundColor: "#f87979",
              data: this.data01
            },
            {
              label: "Data 02",
              backgroundColor: "#79f899",
              data: this.data02
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: 'Line Chart (animation)',
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
    doughnutChart() {
      const ctx = document.getElementById('doughnut');
      this.myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "Data 01",
              backgroundColor: this.colors,
              data: this.data01
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: 'Doughnut Chart',
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
      this.myChart = new Chart(ctx, {
        type: 'polarArea',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "Data 01",
              backgroundColor: this.colors,
              data: this.data01
            }
          ]
        },
        options: {
          plugins: {
            title: {
              display: true,
              text: 'PolarArea Chart',
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
    this.barChart();
    this.lineChart();
    this.doughnutChart();
    this.polarAreaChart();
  },
  data() {
    return {
      myChart : null,
      labels: [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
      ],
      data01: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11],
      data02: [50, 30,  5,  7, 15, 15, 13, 90, 50, 40, 20, 22],
      colors: [
          "#e6440e",
          "#e67a0e",
          "#edcc0e",
          "#c0ed0e",
          "#81ed0e",
          "#0eed9f",
          "#0eede9",
          "#0e94ed",
          "#0e46ed",
          "#900eed",
          "#da0eed",
          "#451626"
      ]
    };
  }
}
</script>

<style>
</style>