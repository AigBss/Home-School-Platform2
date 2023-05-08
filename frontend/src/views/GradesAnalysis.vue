<template>
  <div class="grades-analysis">
    <div class="return-btn">
      <router-link to="/studentdashboard/studentprofile">返回</router-link>
    </div>
    <div class="chart-section">
      <h3>分数变化折线图（多个科目和总分）</h3>
      <div ref="gradesChartLine" class="chart-container"></div>
    </div>
    <div class="chart-section">
      <h3>科目成绩柱状图</h3>
      <div ref="gradesChartBar" class="chart-container"></div>
    </div>
    <div class="chart-section">
      <h3>每门科目占当学期总分的饼状图</h3>
      <div ref="gradesChartPie" class="chart-container"></div>
      </div>
    </div>
</template>


<script>
import * as echarts from 'echarts';
import axios from 'axios';


export default {
  data() {
    return {
      gradesData: [],
      terms: [],
    };
  },
  async mounted() {
    await this.fetchGradesData();
    this.initLineChart();
    this.initBarChart();
    this.initPieChart();
  },
  methods: {
    async fetchGradesData() {
      try {
        const userString = localStorage.getItem('user');
        if (userString) {
          const user = JSON.parse(userString);
          if (user && user.id) {
            const response = await axios.get(`http://localhost:8080/grades/user/${user.id}`);
            if (response.status === 200) {
              this.gradesData = response.data;
            }
          }
        }
      } catch (error) {
        console.error('Error fetching grades data:', error);
      }
    },

    initLineChart() {
      const chartLine = echarts.init(this.$refs.gradesChartLine);
      const subjects = Array.from(new Set(this.gradesData.map((item) => item.subject)));
      const terms = Array.from(new Set(this.gradesData.map((item) => item.term)));

      const seriesData = subjects.map((subject) => {
        const data = terms.map((term) => {
          const termData = this.gradesData.filter((item) => item.term === term && item.subject === subject);
          const totalScore = termData.reduce((acc, item) => acc + item.score, 0);
          return totalScore;
        });

        return {
          name: subject,
          type: 'line',
          data,
        };
      });

      const optionLine = {
        tooltip: {},
        legend: {
          data: subjects,
        },
        xAxis: {
          type: 'category',
          data: terms,
        },
        yAxis: {
          type: 'value',
        },
        series: seriesData,
      };

      const totalScoresLine = {
        name: '总分',
        type: 'line',
        data: terms.map((term) => {
          const termData = this.gradesData.filter((item) => item.term === term);
          const totalScore = termData.reduce((acc, item) => acc + item.score, 0);
          return totalScore;
        }),
      };

      optionLine.series.push(totalScoresLine);
      chartLine.setOption(optionLine);
    },
    initBarChart() {
      const chartBar = echarts.init(this.$refs.gradesChartBar);
      const subjects = Array.from(new Set(this.gradesData.map((item) => item.subject)));
      const terms = Array.from(new Set(this.gradesData.map((item) => item.term)));

      const seriesData = terms.map((term) => {
        const data = subjects.map((subject) => {
          const termData = this.gradesData.filter((item) => item.term === term && item.subject === subject);
          const totalScore = termData.reduce((acc, item) => acc + item.score, 0);
          return totalScore;
        });

        return {
          name: `学期${term}`,
          type: 'bar',
          data,
        };
      });

      const optionBar = {
        tooltip: {},
        legend: {
          data: terms.map((term) => `学期${term}`),
        },
        xAxis: {
          type: 'category',
          data: subjects,
        },
        yAxis: {
          type: 'value',
        },
        series: seriesData,
      };

      chartBar.setOption(optionBar);
    },

    initPieChart() {
      const chartPie = echarts.init(this.$refs.gradesChartPie);
      const subjects = Array.from(new Set(this.gradesData.map((item) => item.subject)));
      const terms = Array.from(new Set(this.gradesData.map((item) => item.term)));

      const termData = terms.map((term) => {
        const data = subjects.map((subject) => {
          const subjectData = this.gradesData.filter((item) => item.subject === subject && item.term === term);
          const totalScore = subjectData.reduce((acc, item) => acc + item.score, 0);
          return {
            name: subject,
            value: totalScore,
          };
        });
        return {
          term,
          data,
        };
      });

      const optionPie = {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)',
        },
        legend: {
          data: subjects,
        },
        series: termData.map((item) => ({
          name: `学期${item.term}`,
          type: 'pie',
          radius: '50%',
          data: item.data,
        })),
      };

      chartPie.setOption(optionPie);
    },
  },
}


</script>


<style scoped>
.chart-container {
  width: 100%;
  height: 400px;
}

.chart-section {
  margin-bottom: 2rem;
}

.return-btn {
  position: absolute;
  top: 1rem;
  right: 1rem;
}
</style>
