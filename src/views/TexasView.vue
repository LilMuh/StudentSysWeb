<template>
  <div>
    <div style="text-align: center; font-size: 20px">
      牌面是：
      <div style="font-size: 40px; margin-top: 10px">
        {{table}}
      </div>
      <hr>
      <el-button @click="handOut">发牌</el-button>
      {{user.username}}, 你的手牌是：
      <div style="font-size: 40px; margin-top: 10px">
      {{hand}}
    </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      table:'3',
      hand:'123',
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    };
  },
  methods: {
    handOut() {
      // Send a request to a backend API to check credentials
      request.post("/admin/TexasHoldem", this.user.username).then(res => {
        if (res.code === '0') {
          // If successes, render player's hand
          console.log(res.data)
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })

    },
  }
}
</script>
