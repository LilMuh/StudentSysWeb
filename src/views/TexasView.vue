<template>
  <div style="text-align: center; font-size: 20px">
    <el-button @click="startGame" size="large">Deal cards</el-button>
    <el-button @click="showNext" size="large" v-show="showNextBtn">Show next</el-button>
    <br>
    <div class="communityCardRow" v-show="showCards">
      <div class="card" v-for="(card, index) in communityCards" :key="index">
        <el-image
            style="width: 100px; height: 150px"
            :src="getImageUrl(card, index)"
        ></el-image>
      </div>
    </div>
    <hr style="margin: 15px">
    {{ user.username }}, Your hand：
    <div class="playerCardRow" v-show="showCards">
      <div class="card" v-for="(card, index) in playerHand" :key="index">
        <el-image
            style="width: 100px; height: 150px"
            :src="getImageUrl(card)"
        ></el-image>
      </div>
    </div>
    <div class="handRank" style="color: indianred; margin-top: 15px">{{ handRank }}</div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      playerHand: [],
      communityCards: [],
      handRank: '',
      user: JSON.parse(localStorage.getItem("user") || "{}"),
      player: {},
      showCards: false,
      showNextBtn: false,
      nextCardCounter: 0,
    };
  },
  methods: {
    startGame() {
      this.setPlayer(this.user);
      this.nextCardCounter= 0,
      // Send a request to a backend API to check credentials
      request.post("/admin/TexasHoldem/startGame", this.player).then(res => {
        if (res.code === '0') {
          this.communityCards = res.data.communityCards.map(card =>
              `${card.rank}_of_${card.suit}`.toLowerCase()
          );
          this.playerHand = res.data.players[res.data.players.length - 1].hand.cards.map(card =>
              `${card.rank}_of_${card.suit}`.toLowerCase()
          );
          this.handRank = res.data.players[res.data.players.length - 1].hand.handRank;
          this.showCards = true;
          this.showNextBtn = true;
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      });
    },
    showNext() {
      this.nextCardCounter++;
    },
    setPlayer(user) {
      this.player.id = user.id;
      this.player.name = user.username;
      this.player.chips = 1000;
      this.player.status = "prepare";
      this.player.currentBet = 100;
    },
    getImageUrl(card, index = -1) {
      if (index === 3 && this.nextCardCounter < 1) {
        return 'http://localhost:8080/cardImage/back_of_back.png';
      }
      if (index === 4 && this.nextCardCounter < 2) {
        return 'http://localhost:8080/cardImage/back_of_back.png';
      }
      return `http://localhost:8080/cardImage/${card}.png`;
    },
  },
}
</script>


<style>
.communityCardRow, .playerCardRow {
  display: flex;
  flex-direction: row;
  margin-top: 15px;
  justify-content: center;
  align-items: center;
}

.card {
  margin-right: 10px;
}
</style>