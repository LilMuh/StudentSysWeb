<template>
  <div class="login-container">
    <div class="login-box">
      <h2 class="login-heading">Forget username or password</h2>
      <form @submit.prevent="checkUser">
        <div class="form-group">
          <label style="margin-bottom: 10px">Worker ID</label>
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
          <label style="margin-bottom: 10px">Phone Number</label>
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
        <button class="register-button-disabled" type="submit">Continue</button>
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
      admin:{
        workerID: "",
        phoneNumber: "",
      },
      validate: {
        isUsernameValid: false,
        isPasswordValid: false,
        isConfirmPasswordValid: false,
        isWorkerIDValid: false,
        isPhoneValid: false,
      }
    };
  },
  methods: {
    checkUser() {
      // Send a request to a backend API to check credentials
      request.post("/admin/forget", this.admin).then(res => {
        if (res.code === '0') {
          // If find user, redirect to change password page
          this.$message({
            message: 'Please change your password',
            type: 'success'
          });
          localStorage.setItem("user", JSON.stringify(res.data));
          this.$router.push("/changePassword");
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
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
      if (this.validate.isWorkerIDValid) {
        this.ReadyRegistration();
      }
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
      if (this.validate.isPhoneValid) {
        this.ReadyRegistration();
      }
    },
    ReadyRegistration() {
      if (
          this.validate.isWorkerIDValid === true &&
          this.validate.isPhoneValid === true
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
