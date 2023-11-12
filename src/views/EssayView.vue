<template>
  <div>

    <!--    Tool bar on the top (Search/ Clear/ Add)    -->
    <div style="margin-top: 10px">
      <el-input placeholder="Course" v-model="params.course" clearable style="width: 150px;"></el-input>
      <el-input placeholder="Title" v-model="params.name" clearable
                style="width: 300px; margin-left: 10px; margin-right: 0px"></el-input>
      <el-input placeholder="Year" v-model="params.year" clearable
                style="width: 150px; margin-left: 10px; margin-right: 0px"></el-input>
      <el-button type="warning" icon="el-icon-search" style="margin-left: 15px;" @click="search()" plain>Search
      </el-button>
      <el-button type="warning" style="margin-left: 10px;" icon="el-icon-refresh-right" @click="clear()" plain>Clear
      </el-button>
      <el-button type="primary" style="margin-left: 10px;" icon="el-icon-plus" @click="add()" plain>Add</el-button>
    </div>

    <!--    Main table with data    -->
    <div>
      <el-table :data="tableData"
                style="width: 100%; margin-top: 22px; font-size: 13px"
                :row-style="{height: '60px'}">
        <el-table-column prop="course" label="Course" width="150">
        </el-table-column>
        <el-table-column prop="name" label="Title" width="750">
        </el-table-column>
        <el-table-column prop="year" label="Year" width="150">
        </el-table-column>
        <el-table-column prop="command" label="Command">
          <template slot-scope="scope">
            <el-button type="primary" plain @click="download(scope.row.link)" style="" icon="el-icon-download">
              Download
            </el-button>
            <el-button type="primary" plain @click="edit(scope.row)" style="margin-right: 10px" icon="el-icon-edit">
              Modify
            </el-button>
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
      <el-dialog title="Essay information" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="Title" label-width="30%">
            <div>
              <div class="input-container">
                <el-input
                    placeholder="Alzheimer's Disease (3rd edition)"
                    v-model="form.name"
                    autocomplete="off"
                    style="width: 65%"
                    class="input-field"
                ></el-input>
              </div>
            </div>
          </el-form-item>
          <el-form-item label="Course" label-width="30%">
            <div>
              <div class="input-container">
                <el-input class="input-field" placeholder="PSY312"
                          v-model="form.course" autocomplete="off"
                          style="width:65%"></el-input>
              </div>
            </div>
          </el-form-item>
          <el-form-item label="Year" label-width="30%">
            <div>
              <div class="input-container">
                <el-input class="input-field" placeholder="2023"
                          v-model="form.year" autocomplete="off"
                          style="width:65%"></el-input>
              </div>
            </div>
          </el-form-item>
          <el-form-item label="File" label-width="30%">
            <div>
              <el-upload
                  action="http://localhost:8080/tomH_Page/files/upload"
                  :on-success="successUpload"
                  :limit="1"
                  :on-exceed="handleExceed"
              >
                <el-button size="small" round plain>Upload from device</el-button>
              </el-upload>
            </div>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">Cancel</el-button>
          <el-button class="submit" type="primary" @click="submit()">Confirm</el-button>
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
        name: '',
        course: '',
        year: '',
        pageNum: 1,
        pageSize: 10,
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
      request.get("/essay/search", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    clear() {
      this.params = {
        name: '',
        course: '',
        year: '',
        link:'',
        pageNum: 1,
        pageSize: 10,
      },
          this.search()
    },
    add() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    successUpload(res) {
      console.log(res)
    },
    edit(accountInfo) {
      this.form = accountInfo;
      this.dialogFormVisible = true
      this.search()
    },
    download(link) {
      console.log("Download: "+link)
      this.search()
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.search()
    },
    del(accountID) {
      request.delete("/essay/" + accountID).then(res => {
        if (res.code === '0') {
          this.$message.success("Success!");
          this.search()
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    cancel() {
      this.dialogFormVisible = false;
      this.search()
      this.form = {};
    },
    submit() {
      request.post("/essay", this.form).then(res => {
        if (res.code === '0') {
          this.$message.success("Success!");
          this.dialogFormVisible = false;
          this.search()
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    handleExceed(files, fileList) {
      this.$message.warning(`You can only upload 1 file at each time`);
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

</style>


