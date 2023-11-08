<template>
  <div class="login-container">
    <div class="login-box">
      <h2 class="login-heading">Hi, {{user.username}}!</h2>
      <form @submit.prevent="changePassword">
        <div class="form-group">
          <label style="margin-bottom: 5px">New Password</label>
          <div class="input-container">
            <el-input
                type="password"
                id="password"
                v-popover:password
                v-model="admin.password"
                placeholder="Enter your new password"
                @input="checkPassword"
                size="large"
            ></el-input>
            <div class="validation-icons">
              <i
                  class="validation-icon-check"
                  v-if="admin.password && validate.isPasswordValid"
                  :class="'el-icon-circle-check'"
              ></i>
              <i
                  class="validation-icon-cross"
                  v-if="admin.password && !validate.isPasswordValid"
                  :class="'el-icon-circle-close'"
              ></i>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label style="margin-bottom: 5px">Confirm Password</label>
          <div class="input-container">
            <el-input
                type="password"
                id="confirm-password"
                v-model="confirmPassword"
                placeholder="Confirm your password"
                @input="checkConfirmPassword"
                size="large"
            ></el-input>
            <div class="validation-icons">
              <i
                  class="validation-icon-check"
                  v-if="confirmPassword && validate.isConfirmPasswordValid"
                  :class="'el-icon-circle-check'"
              ></i>
              <i
                  class="validation-icon-cross"
                  v-if="confirmPassword && !validate.isConfirmPasswordValid"
                  :class="'el-icon-circle-close'"
              ></i>
            </div>
          </div>
        </div>
        <el-popover
            ref="password"
            placement="right"
            width="50"
            trigger="focus">
          <div style="padding: 3px">
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>At least: </strong>
            </div>
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>1</strong> uppercase letter
            </div>
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>1</strong> lowercase letter
            </div>
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>1</strong> number
            </div>
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>1</strong> symbol
            </div>
            <div style="font-size: 13px">
              Length: <strong>at least 8</strong>
            </div>
          </div>
        </el-popover>
        <button class="register-button-disabled" type="submit">Confirm</button>
      </form>
      <router-link to="/login" class="back-link"><i class="el-icon-arrow-left"></i>Back</router-link>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      confirmPassword: "",
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      admin:{
        id:"",
        username: "",
        password: "",
        workerID: "",
        phoneNumber: "",
      },
      validate: {
        isPasswordValid: false,
        isConfirmPasswordValid: false,
      }
    };
  },
  methods: {
    changePassword() {
      this.admin.id=this.user.id;
      this.admin.username=this.user.username;
      this.admin.workerID=this.user.workerID;
      this.admin.phoneNumber=this.user.phoneNumber;
      // Send a request to a backend API to check credentials
      request.post("/admin/changePassword", this.admin).then(res => {
        if (res.code === '0') {
          // If successfully changed password, redirect to login page
          this.$message({
            message: 'Successfully set password!',
            type: 'success'
          });
          localStorage.removeItem("user");
          this.$router.push("/login");
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    checkPassword() {
      const password = this.admin.password;

      // Check if the password has at least one uppercase letter
      const hasUppercase = /[A-Z]/.test(password);

      // Check if the password has at least one lowercase letter
      const hasLowercase = /[a-z]/.test(password);

      // Check if the password has at least one number
      const hasNumber = /[0-9]/.test(password);

      // Check if the password is longer than 8 characters
      const isLengthValid = password.length > 8;

      // Check if the password has at least one special symbol (, . !)
      const hasSpecialSymbol = /[,.!@#$%^&*_+=`~/*?<>;':]/.test(password);

      // Check if the confirmed password is the same
      const sameConfirmed = this.admin.password === this.confirmPassword;

      // Check all conditions
      this.validate.isPasswordValid =
          hasUppercase && hasLowercase && hasNumber && isLengthValid && hasSpecialSymbol;
      if (sameConfirmed) {
        this.validate.isConfirmPasswordValid = true;
      } else {
        this.validate.isConfirmPasswordValid = false;
      }
      if (this.validate.isPasswordValid) {
        this.ReadyRegistration();
      }
    },
    checkConfirmPassword() {
      this.validate.isConfirmPasswordValid = this.admin.password === this.confirmPassword;
      if (this.validate.isConfirmPasswordValid) {
        this.ReadyRegistration();
      }
    },
    ReadyRegistration() {
      if (
          this.validate.isPasswordValid === true &&
          this.validate.isConfirmPasswordValid === true
      ) {
        const btn = document.querySelector(".register-button-disabled");
        btn.classList.remove("register-button-disabled");
        btn.classList.add("register-button");
      }
    }
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

.back-link {
  width:15%;
  text-align: center;
  display: block;
  color: #067fc9; /* Blue color for links */
  font-size: 16px;
  text-decoration: underline;
  margin:auto;
  margin-top: 15px;
  padding-right: 7px;
}

.register-button {
  width: 100%;
  padding: 12px;
  background-color: #027dc9;
  color: #fff9f1;
  border: none;
  border-radius: 10px;
  margin-top: 5px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.2s;
}

.register-button-disabled {
  width: 100%;
  padding: 12px;
  background-color: #979797;
  color: #fff9f1;
  border: none;
  border-radius: 10px;
  margin-top: 5px;
  font-size: 18px;
  pointer-events: none;
}

.register-button:hover {
  background-color: #0056b3;
}

.validation-icon-cross {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 10px;
  font-size: 18px;
  color: #d00000;
  margin-right: -145px;
}

.validation-icon-check {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 10px;
  font-size: 18px;
  color: #28c700;
  margin-right: -145px;
}
</style>
