<template>
  <div>

    <!--    Tool bar on the top (Search/ Clear/ Add)    -->
    <div style="margin-top: 10px">
      <el-input placeholder="username" v-model="params.username" clearable style="width: 200px;"></el-input>
      <el-input placeholder="workerID" v-model="params.workerID" clearable
                style="width: 200px; margin-left: 10px; margin-right: 10px"></el-input>
      <el-input placeholder="phone" v-model="params.phone" clearable style="width: 200px;"></el-input>
      <el-button type="warning" style="margin-left: 15px;" @click="search()" plain>Search</el-button>
      <el-button type="warning" style="margin-left: 10px;" @click="clear()" plain>Clear</el-button>
      <el-button type="primary" style="margin-left: 10px;" @click="add()" plain>Add</el-button>
    </div>

    <!--    Main table with data    -->
    <div>
      <el-table :data="tableData"
                style="width: 100%; margin-top: 22px"
                :row-style="{height: '60px'}">
        <el-table-column prop="id" label="ID" width="50">
        </el-table-column>
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
            <el-button type="primary" plain @click="edit(scope.row)" style="margin-right: 10px">Modify</el-button>
            <el-popconfirm
                confirm-button-text='OK'
                cancel-button-text='Cancel'
                icon="el-icon-info"
                icon-color="red"
                title="Are you sure to delete？"
                @confirm="del(scope.row.id)"
            >
              <el-button type="danger" plain slot="reference">Delete</el-button>
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
            <el-input v-popover:username placeholder="abc123" v-model="form.username" autocomplete="off" style=" width:55%"></el-input>
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
            <el-input v-popover:password placeholder="Abc12345." v-model="form.password" autocomplete="off" style=" width:55%"></el-input>
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
            <el-input v-popover:workerID placeholder="111111000011110010" v-model="form.workerID" autocomplete="off" style=" width:55%"></el-input>
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
            <el-input v-popover:phone placeholder="4168886666" v-model="form.phoneNumber" autocomplete="off" style=" width:55%"></el-input>
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
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="submit()">Confirm</el-button>
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
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      form: {}
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
      this.form = accountInfo;
      this.dialogFormVisible = true
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
    }

  }

}
</script>

