<template>
  <div>
    <el-container>
      <el-header style="background-color: rgb(78, 84, 90);">
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px;">
        <span style="font-size: 20px; margin-left: 15px; color: white;">Student System</span>
        <el-dropdown trigger="click" style="float: right; line-height: 60px; height: 60px">
          <span class="el-dropdown-link">{{ user.username }}<i class="el-icon-user el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <div @click="logout">
              <el-dropdown-item icon="el-icon-switch-button">Log out</el-dropdown-item>
            </div>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
    </el-container>

    <el-container>
      <el-aside style="overflow: hidden; min-height: 100vh; background-color: #555c63;" width="250px">
        <el-menu default-active="$route.path" router class="el-menu-vertical-demo"
                 background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="/">
            <i class="el-icon-menu"></i>
            <span slot="title">Main page</span>
          </el-menu-item>

          <el-submenu index="/Accounts">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>Accounts</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/Admin">Admin</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="Portfolio">
            <template slot="title">
              <i class="el-icon-receiving"></i>
              <span>Portfolio</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/Design">Graphic design</el-menu-item>
              <el-menu-item index="/Essay">Essay</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="Games">
            <template slot="title">
              <i class="el-icon-receiving"></i>
              <span>Games</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/TexasHoldem">Texas Hold'em</el-menu-item>
              <el-menu-item index="/">Bi-color ball</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Layout",

  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },

  methods: {
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/login")
    },
  }
}
</script>

<style scoped>
.el-menu {
  border-right: none !important;
}

.el-dropdown-link {
  padding: 6px 6px 6px 9px;
  border-radius: 9px;
  margin-top: 15px;
  color: #fcfcfc;
  font-size: 14px;
}

.el-dropdown-link:hover {
  background-color: rgb(72, 78, 85);
  cursor: pointer;
}
</style>