<template>
  <div class="login-container">
    <div class="login-box">
      <h2 class="login-heading">Admin Login</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username" style="margin-bottom: 5px">Username</label>
          <input
              type="text"
              id="username"
              v-model="admin.username"
              placeholder="Enter your username"
          />
        </div>
        <div class="form-group">
          <label for="password" style="margin-bottom: 5px">Password</label>
          <input
              type="password"
              id="password"
              v-model="admin.password"
              placeholder="Enter your password"
          />
        </div>
        <button class="login-button" type="submit">Login</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      admin:{
        username: "",
        password: "",
      }
    };
  },
  methods: {
    login() {
      // Send a request to a backend API to check credentials
      request.post("/admin/login", this.admin).then(res => {
        if (res.code === '0') {
          // If login is successful, redirect to another page
          this.$message({
            message: 'Successfully Logged in',
            type: 'success'
          });
          console.log("Further manipulation:", res.data)
          this.$router.push("/");
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })

    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #333; /* Dark Gray background */
}

.login-box {
  background-color: #444; /* Slightly Darker Gray background */
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  max-width: 500px;
  width: 100%;
  font-family: 'Arial', sans-serif;
}

.login-heading {
  text-align: center;
  color: #fff; /* White text */
  font-size: 26px;
  margin-bottom: 20px;
}

.form-group {
  margin: 25px 0;
}

label {
  display: block;
  font-weight: bold;
  color: #ccc; /* Light Gray text */
  font-size: 16px;
}

input {
  width: 100%;
  padding: 12px;
  border: 1px solid #555; /* Slightly darker border */
  border-radius: 5px;
  font-size: 16px;
}

.login-button {
  width: 100%;
  padding: 12px;
  margin-top: 5px;
  background-color: #027dc9; /* Blue button color */
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.2s;
}

.login-button:hover {
  background-color: #0056b3; /* Darker blue on hover */
}
</style>
