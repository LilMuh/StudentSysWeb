<template>
  <div>

    <!--    Tool bar on the top (Search/ Clear/ Add)    -->
    <div style="margin-top: 10px">
      <el-input placeholder="username" v-model="params.username" clearable style="width: 200px;"></el-input>
      <el-input placeholder="workerID" v-model="params.workerID" clearable
                style="width: 200px; margin-left: 10px; margin-right: 10px"></el-input>
      <el-input placeholder="phone" v-model="params.phone" clearable style="width: 200px;"></el-input>
      <el-button type="warning" icon="el-icon-search" style="margin-left: 15px;" @click="search()" plain>Search</el-button>
      <el-button type="warning" style="margin-left: 10px;" icon="el-icon-refresh-right" @click="clear()" plain>Clear</el-button>
      <el-button type="primary" style="margin-left: 10px;" icon="el-icon-plus" @click="add()" plain>Add</el-button>
    </div>

    <!--    Main table with data    -->
    <div>
      <el-table :data="tableData"
                style="width: 100%; margin-top: 22px"
                :row-style="{height: '60px'}">

        <el-table-column prop="username" label="username" width="180">
        </el-table-column>
        <el-table-column prop="password" label="password" width="180">
        </el-table-column>
        <el-table-column prop="workerID" label="workerID" width="250">
        </el-table-column>
        <el-table-column prop="phoneNumber" label="phonenumber">
        </el-table-column>
        <el-table-column prop="command" label="Command">
          <template slot-scope="scope">
            <el-button type="primary" plain @click="edit(scope.row)" style="margin-right: 10px" icon="el-icon-edit">Modify</el-button>
            <el-popconfirm
                confirm-button-text='OK'
                cancel-button-text='Cancel'
                icon="el-icon-info"
                icon-color="red"
                title="Are you sure to delete？"
                @confirm="del(scope.row.id)"
            >
              <el-button type="danger" plain slot="reference" icon="el-icon-delete-solid">Delete</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--    Pagination    -->
    <div style="margin-top: 30px">
      <el-pagination
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <!--    Add account information    -->
    <div>
      <el-dialog title="Account information" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="Username" label-width="30%">
            <div>
              <div class="input-container">
                <el-input
                    v-popover:username
                    placeholder="abc123"
                    v-model="form.username"
                    autocomplete="off"
                    @input="checkUsername"
                    style="width: 55%"
                    class="input-field"
                ></el-input>
                <div class="validation-icons">
                  <i
                      class="validation-icon-check el-icon-circle-check"
                      v-show="form.username && validate.isUsernameValid"
                  ></i>
                  <i
                      class="validation-icon-cross el-icon-circle-close"
                      v-show="form.username && !validate.isUsernameValid"
                  ></i>
                </div>
              </div>
            </div>
          </el-form-item>
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
          <el-form-item label="Password" label-width="30%">
            <div>
              <div class="input-container">
                <el-input class="input-field" @input="checkPassword" v-popover:password placeholder="Abc12345."
                          v-model="form.password" autocomplete="off"
                          style=" width:55%"></el-input>
                <div class="validation-icons">
                  <i
                      class="validation-icon-check"
                      v-if="form.password && validate.isPasswordValid"
                      :class="'el-icon-circle-check'"
                  ></i>
                  <i
                      class="validation-icon-cross"
                      v-if="form.password && !validate.isPasswordValid"
                      :class="'el-icon-circle-close'"
                  ></i>
                </div>
              </div>
            </div>
          </el-form-item>
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
          <el-form-item label="Worker ID" label-width="30%">
            <div>
              <div class="input-container">
                <el-input class="input-field" @input="checkWorkerID" v-popover:workerID placeholder="456693011021941121"
                          v-model="form.workerID"
                          autocomplete="off"
                          style=" width:55%">
                </el-input>
                <div class="validation-icons">
                  <i
                      class="validation-icon-check"
                      v-if="form.workerID && validate.isWorkerIDValid"
                      :class="'el-icon-circle-check'"
                  ></i>
                  <i
                      class="validation-icon-cross"
                      v-if="form.workerID && !validate.isWorkerIDValid"
                      :class="'el-icon-circle-close'"
                  ></i>
                </div>
              </div>
            </div>
          </el-form-item>
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
          <el-form-item label="Phone number" label-width="30%">
            <div>
              <div class="input-container">
                <el-input class="input-field" @input="checkPhone" v-popover:phone placeholder="2894802860"
                          v-model="form.phoneNumber"
                          autocomplete="off"
                          style=" width:55%"></el-input>
                <div class="validation-icons">
                  <i
                      class="validation-icon-check"
                      v-if="form.phoneNumber && validate.isPhoneValid"
                      :class="'el-icon-circle-check'"
                  ></i>
                  <i
                      class="validation-icon-cross"
                      v-if="form.phoneNumber && !validate.isPhoneValid"
                      :class="'el-icon-circle-close'"
                  ></i>
                </div>
              </div>
            </div>
          </el-form-item>
          <el-popover
              ref="phone"
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
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">Cancel</el-button>
          <el-button class="submit" type="primary" :disabled="isSubmitDisabled" @click="submit()">Confirm</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>


<script>

import request from "@/utils/request";

export default {
  data() {
    return {
      params: {
        username: '',
        workerID: '',
        phone: '',
        pageNum: 1,
        pageSize: 10,
      },
      validate: {
        isUsernameValid: false,
        isPasswordValid: false,
        isConfirmPasswordValid: false,
        isWorkerIDValid: false,
        isPhoneValid: false,
      },
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      form: {},
      isSubmitDisabled: true,
    }
  },
  // do something at page creation
  created() {
    this.search();
  },
  // some events happening on the page
  methods: {
    search() {
      request.get("/admin/search", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {
        }
      })
    },
    clear() {
      this.params = {
        username: '',
        workerID: '',
        phone: '',
        pageNum: 1,
        pageSize: 10,
      }
      this.search()
    },
    add() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    edit(accountInfo) {
      this.checkUsernameModify(accountInfo.username);
      this.checkPasswordModify(accountInfo.password);
      this.checkWorkerIDModify(accountInfo.workerID);
      this.checkPhoneModify(accountInfo.phoneNumber);
      this.form = accountInfo;
      this.dialogFormVisible = true
      this.search()
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.search()
    },
    del(accountID) {
      request.delete("/admin/" + accountID).then(res => {
        if (res.code === '0') {
          this.$message({
            message: 'Successfully deleted!',
            type: 'success'
          });
          this.search()
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    cancel(){
      this.dialogFormVisible = false;
      this.search()
    },
    submit() {
      request.post("/admin", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            message: 'Success',
            type: 'success'
          });
          this.dialogFormVisible = false;
          this.search()
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    checkUsername() {
      const username = this.form.username;

      // Check if the username's length is within 3 to 15 characters
      const isLengthValid = username.length >= 3 && username.length <= 15;

      // Check if the username contains only alphanumeric characters
      const isAlphanumeric = /^[a-zA-Z0-9]+$/.test(username);

      // Check if there is at least one letter in the username
      const hasLetter = /[a-zA-Z]/.test(username);

      // Check all conditions
      this.validate.isUsernameValid = isLengthValid && isAlphanumeric && hasLetter;
      this.checkReadyRegistration();
    },
    checkPassword() {
      const password = this.form.password;

      // Check if the password has at least one uppercase letter
      const hasUppercase = /[A-Z]/.test(password);

      // Check if the password has at least one lowercase letter
      const hasLowercase = /[a-z]/.test(password);

      // Check if the password has at least one number
      const hasNumber = /[0-9]/.test(password);

      // Check if the password is longer than 8 characters
      const isLengthValid = password.length > 8;

      // Check if the password has at least one special symbol (, . !)
      const hasSpecialSymbol = /[,.!@#$%^&*_+=`~/?<>;':]/.test(password);

      // Check all conditions
      this.validate.isPasswordValid =
          hasUppercase && hasLowercase && hasNumber && isLengthValid && hasSpecialSymbol;
      this.checkReadyRegistration();
    },
    checkWorkerID() {
      const workerID = this.form.workerID;

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
      this.checkReadyRegistration();
    },
    checkPhone() {
      const phoneNumber = this.form.phoneNumber;

      // Check if the phoneNumber number is of length 10
      const isLengthValid = phoneNumber.length === 10;

      // Check if the phoneNumber number does not start with 0
      const doesNotStartWithZero = phoneNumber[0] !== "0";

      // Check if the phoneNumber number consists of pure numbers
      const hasPureNumbers = /^[0-9]{10}$/.test(phoneNumber);

      // Check all conditions
      this.validate.isPhoneValid =
          isLengthValid && doesNotStartWithZero && hasPureNumbers;
      this.checkReadyRegistration();
    },
    checkReadyRegistration() {
      // Check if all fields meet the required format criteria
      this.isSubmitDisabled =
          !this.validate.isUsernameValid ||
          !this.validate.isPasswordValid ||
          !this.validate.isWorkerIDValid ||
          !this.validate.isPhoneValid;
    },
    // Modify
    checkUsernameModify(username) {
      // Check if the username's length is within 3 to 15 characters
      const isLengthValid = username.length >= 3 && username.length <= 15;

      // Check if the username contains only alphanumeric characters
      const isAlphanumeric = /^[a-zA-Z0-9]+$/.test(username);

      // Check if there is at least one letter in the username
      const hasLetter = /[a-zA-Z]/.test(username);

      // Check all conditions
      this.validate.isUsernameValid = isLengthValid && isAlphanumeric && hasLetter;
      this.checkReadyRegistration();
    },
    checkPasswordModify(password) {
      // Check if the password has at least one uppercase letter
      const hasUppercase = /[A-Z]/.test(password);

      // Check if the password has at least one lowercase letter
      const hasLowercase = /[a-z]/.test(password);

      // Check if the password has at least one number
      const hasNumber = /[0-9]/.test(password);

      // Check if the password is longer than 8 characters
      const isLengthValid = password.length > 8;

      // Check if the password has at least one special symbol (, . !)
      const hasSpecialSymbol = /[,.!@#$%^&*_+=`~/?<>;':]/.test(password);

      // Check all conditions
      this.validate.isPasswordValid =
          hasUppercase && hasLowercase && hasNumber && isLengthValid && hasSpecialSymbol;
      this.checkReadyRegistration();
    },
    checkWorkerIDModify(workerID) {
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
      this.checkReadyRegistration();
    },
    checkPhoneModify(phoneNumber) {
      // Check if the phoneNumber number is of length 10
      const isLengthValid = phoneNumber.length === 10;

      // Check if the phoneNumber number does not start with 0
      const doesNotStartWithZero = phoneNumber[0] !== "0";

      // Check if the phoneNumber number consists of pure numbers
      const hasPureNumbers = /^[0-9]{10}$/.test(phoneNumber);

      // Check all conditions
      this.validate.isPhoneValid =
          isLengthValid && doesNotStartWithZero && hasPureNumbers;
      this.checkReadyRegistration();
    },
  }

}
</script>

<style>
.input-container {
  position: relative;
  display: flex;
  align-items: center;
}

.validation-icons {
  position: absolute;
  transform: translateY(-50%);
  left: 255px; /* Adjust the left position as needed */
  top: 18px;
}

.input-field {
  width: 55%;
}

.validation-icon-cross,
.validation-icon-check {
  font-size: 18px;
}

.validation-icon-cross {
  color: #de3131;
}

.validation-icon-check {
  color: #5dd938;
}
</style>


