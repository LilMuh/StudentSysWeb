<template>
  <div>
    <div style="margin-top: 10px">
      <el-input placeholder="username" v-model="params.username" clearable style="width: 200px;"></el-input>
      <el-input placeholder="workerID" v-model="params.workerID" clearable
                style="width: 200px; margin-left: 10px; margin-right: 10px"></el-input>
      <el-input placeholder="phone" v-model="params.phone" clearable style="width: 200px;"></el-input>
      <el-button type="warning" style="margin-left: 15px;" @click="search()" plain>Search</el-button>
      <el-button type="warning" style="margin-left: 10px;" @click="clear()" plain>Clear</el-button>
      <el-button type="primary" style="margin-left: 10px;" @click="add()" plain>Add</el-button>
    </div>

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

    <div style="margin-top: 30px">
      <el-pagination
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <div>
      <el-dialog title="Please enter account information" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="Username" label-width="30%">
            <el-input v-model="form.username" autocomplete="off" style=" width:65%"></el-input>
          </el-form-item>
          <el-form-item label="Password" label-width="30%">
            <el-input v-model="form.password" autocomplete="off" style=" width:65%"></el-input>
          </el-form-item>
          <el-form-item label="Worker ID" label-width="30%">
            <el-input v-model="form.workerID" autocomplete="off" style=" width:65%"></el-input>
          </el-form-item>
          <el-form-item label="Phone number" label-width="30%">
            <el-input v-model="form.phoneNumber" autocomplete="off" style=" width:65%"></el-input>
          </el-form-item>
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
            type: 'fail'
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
            type: 'fail'
          });
        }
      })
    }

  }

}
</script>

