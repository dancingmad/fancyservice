var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue.js!',
    boards: [],
    currentBoard: {}
  },
  methods: {
    isUnknown: function(field) {
      return field.mineCount == -1 && !field.kill;
    },
    isEmpty: function(field) {
      return field.mineCount == 0 && !field.kill;
    },
    isKilled: function(field) {
      return field.kill;
    },
    isIndicator: function(field) {
      return !field.kill && field.mineCount > 0;
    },
    createGame: function () {
        axios
            .post('http://localhost:9999/api/minesweeper/', {
              size:6
            })
            .then(response => (this.currentBoard = response.data));
    },
    loadGame: function (board) {
       this.currentBoard = board;
    },
    revealAndSubmit: function (subfield) {
        subfield.revealed=true;
        axios
            .put('http://localhost:9999/api/minesweeper/', this.currentBoard)
            .then(response => (this.currentBoard = response.data));
    },
  },
    mounted () {
        axios
            .get('http://localhost:9999/api/minesweeper/')
            .then(response => (this.boards = response.data));
    },
});