<template>
  <div class="register-container">
    <div class="register-box">
      <h2 class="register-heading">Admin Registration</h2>
      <form @submit.prevent="register">
        <div class="form-group">
          <label style="margin-bottom: 5px">Username</label>
          <div class="input-container">
            <el-input
                type="text"
                v-popover:username
                id="username"
                v-model="admin.username"
                placeholder="Enter your username"
                @input="checkUsername"
                size="large"
            ></el-input>
            <div class="validation-icons">
              <i
                  class="validation-icon-check"
                  v-if="admin.username && validate.isUsernameValid"
                  :class="'el-icon-circle-check'"
              ></i>
              <i
                  class="validation-icon-cross"
                  v-if="admin.username && !validate.isUsernameValid"
                  :class="'el-icon-circle-close'"
              ></i>
            </div>

          </div>
        </div>
        <div class="form-group">
          <label style="margin-bottom: 5px">Password</label>
          <div class="input-container">
            <el-input
                type="password"
                id="password"
                v-popover:password
                v-model="admin.password"
                placeholder="Enter your password"
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
        <div class="form-group">
          <label style="margin-bottom: 5px">Worker ID</label>
          <div class="input-container">
            <el-input
                type="text"
                id="workerID"
                v-model="admin.workerID"
                v-popover:workerID
                placeholder="Enter your worker ID"
                @input="checkWorkerID"
                size="large"
            ></el-input>
            <div class="validation-icons">
              <i
                  class="validation-icon-check"
                  v-if="admin.workerID && validate.isWorkerIDValid"
                  :class="'el-icon-circle-check'"
              ></i>
              <i
                  class="validation-icon-cross"
                  v-if="admin.workerID && !validate.isWorkerIDValid"
                  :class="'el-icon-circle-close'"
              ></i>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label style="margin-bottom: 5px">Phone Number</label>
          <div class="input-container">
            <el-input
                type="text"
                id="phoneNumber"
                v-popover:phoneNumber
                v-model="admin.phoneNumber"
                placeholder="Enter your phone number"
                @input="checkPhone"
                size="large"
            ></el-input>
            <div class="validation-icons">
              <i
                  class="validation-icon-check"
                  v-if="admin.phoneNumber && validate.isPhoneValid"
                  :class="'el-icon-circle-check'"
              ></i>
              <i
                  class="validation-icon-cross"
                  v-if="admin.phoneNumber && !validate.isPhoneValid"
                  :class="'el-icon-circle-close'"
              ></i>
            </div>
          </div>
        </div>
        <button class="register-button-disabled" type="submit">Register</button>
        <el-popover
            ref="username"
            placement="right"
            width="50"
            trigger="focus">
          <div style="padding: 3px">
            <div style="margin-bottom: 5px; font-size: 13px">
              Length: <strong>3~15</strong>
            </div>
            <div style="font-size: 13px">
              At least <strong>1</strong> letter
            </div>
          </div>
        </el-popover>
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
        <el-popover
            ref="workerID"
            placement="right"
            width="50"
            trigger="focus">
          <div style="padding: 3px">
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>Pure number</strong>
            </div>
            <div style="margin-bottom: 5px; font-size: 13px">
              Length: <strong>18</strong>
            </div>
            <div style="font-size: 13px">
              Can't start with <strong>0</strong>
            </div>
          </div>
        </el-popover>
        <el-popover
            ref="phoneNumber"
            placement="right"
            width="50"
            trigger="focus">
          <div style="padding: 3px">
            <div style="margin-bottom: 5px; font-size: 13px">
              <strong>Pure number</strong>
            </div>
            <div style="margin-bottom: 5px; font-size: 13px">
              Length: <strong>10</strong>
            </div>
            <div style="font-size: 13px">
              Can't start with <strong>0</strong>
            </div>
          </div>
        </el-popover>
      </form>
    </div>
  </div>
</template>


<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      confirmPassword: "",
      admin:{
        username: "",
        password: "",
        workerID: "",
        phoneNumber: "",
      },
      validate:{
        isUsernameValid: false,
        isPasswordValid: false,
        isConfirmPasswordValid: false,
        isWorkerIDValid: false,
        isPhoneValid: false,
      }
    };
  },
  methods: {
    register() {
      console.log(this.admin)
      // Send a request to a backend API to check credentials
      request.post("/admin/register", this.admin).then(res => {
        if (res.code === '0') {
          // If register is successful, redirect to another page
          this.$message({
            message: 'Successfully registered',
            type: 'success'
          });
          this.$router.push("/login");
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })

    },
    checkUsername() {
      const username = this.admin.username;

      // Check if the username's length is within 3 to 15 characters
      const isLengthValid = username.length >= 3 && username.length <= 15;

      // Check if the username contains only alphanumeric characters
      const isAlphanumeric = /^[a-zA-Z0-9]+$/.test(username);

      // Check if there is at least one letter in the username
      const hasLetter = /[a-zA-Z]/.test(username);

      // Check all conditions
      this.validate.isUsernameValid = isLengthValid && isAlphanumeric && hasLetter;
      if(this.validate.isUsernameValid){
        this.ReadyRegistration();
      }
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
      const sameConfirmed = this.admin.password===this.confirmPassword;

      // Check all conditions
      this.validate.isPasswordValid =
          hasUppercase && hasLowercase && hasNumber && isLengthValid && hasSpecialSymbol;
      if(sameConfirmed){
        this.validate.isConfirmPasswordValid=true;
      }
      else{
        this.validate.isConfirmPasswordValid=false;
      }
      if(this.validate.isPasswordValid){
        this.ReadyRegistration();
      }
    },
    checkConfirmPassword() {
      this.validate.isConfirmPasswordValid = this.admin.password===this.confirmPassword;
      if(this.validate.isConfirmPasswordValid){
        this.ReadyRegistration();
      }
    },
    checkWorkerID() {
      const workerID = this.admin.workerID;

      // Check if the workerID is of length 18
      const isLengthValid = workerID.length === 18;

      // Check if the workerID does not start with 0
      const doesNotStartWithZero = workerID[0] !== "0";

      // Check if the first 17 characters are pure numbers
      const hasPureNumbers = /^[0-9]{17}$/.test(workerID.substring(0, 17));

      // Check if the last character is either a number, lowercase x, or uppercase X
      const isLastCharValid = /[0-9xX]/.test(workerID[17]);

      // Check all conditions
      this.validate.isWorkerIDValid =
          isLengthValid &&
          doesNotStartWithZero &&
          hasPureNumbers &&
          isLastCharValid;
      if(this.validate.isWorkerIDValid){this.ReadyRegistration();}
    },
    checkPhone() {
      const phoneNumber = this.admin.phoneNumber;

      // Check if the phoneNumber number is of length 10
      const isLengthValid = phoneNumber.length === 10;

      // Check if the phoneNumber number does not start with 0
      const doesNotStartWithZero = phoneNumber[0] !== "0";

      // Check if the phoneNumber number consists of pure numbers
      const hasPureNumbers = /^[0-9]{10}$/.test(phoneNumber);

      // Check all conditions
      this.validate.isPhoneValid =
          isLengthValid && doesNotStartWithZero && hasPureNumbers;
      if(this.validate.isPhoneValid){this.ReadyRegistration();}
    },
    ReadyRegistration(){
      if(
          this.validate.isUsernameValid ===  true &&
          this.validate.isPasswordValid ===  true &&
          this.validate.isConfirmPasswordValid ===  true &&
          this.validate.isWorkerIDValid ===  true &&
          this.validate.isPhoneValid ===  true
      ){
        const btn = document.querySelector(".register-button-disabled");
        btn.classList.remove("register-button-disabled");
        btn.classList.add("register-button");
      }
    }
  },
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #333; /* Darker Gray background */
  margin: 0;
  padding: 20px;
  box-sizing: border-box;
}

.register-box {
  background-color: #444; /* Slightly Darker Gray background */
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  max-width: 500px;
  width: 100%;
  font-family: 'Arial', sans-serif;
}

.register-heading {
  text-align: center;
  color: #fff9f1;
  font-size: 24px;
  margin-bottom: 35px;
}

.form-group {
  margin: 20px 0;
}

label {
  display: block;
  font-weight: bold;
  color: #fff9f1;
  font-size: 16px;
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
  /*cursor: pointer;*/
  font-size: 18px;
  transition: background-color 0.2s;
}

.register-button:hover {
  background-color: #0056b3;
}

.input-container {
  position: relative;
}

.validation-icon-cross {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 10px;
  font-size: 18px;
  color: #d00000;
}

.validation-icon-check {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 10px;
  font-size: 18px;
  color: #28c700;
}
</style>
