<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="用户ID" prop="userId">
        <el-input
            v-model="queryParams.userId"
            placeholder="请输入用户ID"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nikeName">
        <el-input
            v-model="queryParams.nikeName"
            placeholder="请输入用户昵称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input
            v-model="queryParams.phone"
            placeholder="请输入手机号码"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
            v-model="queryParams.gender"
            placeholder="请输入性别"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            size="small"
            @click="handleAdd"
            v-hasPermi="['customer:user:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            size="small"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['customer:user:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            size="small"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['customer:user:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            size="small"
            @click="handleExport"
            v-hasPermi="['customer:user:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="用户ID" align="center" prop="userId" width="100"/>
      <el-table-column label="手机号码" align="center" prop="phone" width="100"/>
      <el-table-column label="小程序openId" align="center" prop="miniOpenId" width="100"/>
      <el-table-column label="AppOpenId" align="center" prop="appOpenId"/>
      <el-table-column label="unionid" align="center" prop="unionId"/>
      <el-table-column label="所在国家" align="center" prop="country"/>
      <el-table-column label="所在省份" align="center" prop="province"/>
      <el-table-column label="所在城市" align="center" prop="city"/>
      <el-table-column label="语言" align="center" prop="language" width="100">
        <template #default="scope">
          <dict-tag :options="wx_use_language" :value="scope.row.language" />
        </template>
      </el-table-column>
      <el-table-column label="用户昵称" align="center" prop="nikeName"/>
      <el-table-column label="头像地址" align="center" prop="avatarUrl"/>
      <el-table-column label="邮箱" align="center" prop="email"/>
      <el-table-column label="性别" align="center" prop="gender"/>
      <el-table-column label="最近一次登录时间" align="center" prop="lastLoginTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.lastLoginTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最近一次登录IP地址" align="center" prop="lastLoginIp"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="small"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['customer:user:edit']"
          >修改
          </el-button>
          <el-button
              size="small"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['customer:user:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
    />

    <el-dialog v-model="open" :title="title" width="800px" append-to-body>
      <el-form ref="userRef" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="头像地址" prop="avatarUrl">
              <el-avatar shape="square" :size="100" :src="form.avatarUrl"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="小程序openId" prop="miniOpenId">
              <span v-if="form.miniOpenId != null">{{ form.miniOpenId }}</span>
              <el-tag v-else>暂无</el-tag>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="AppOpenId" prop="appOpenId">
              <span>{{ form.appOpenId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="unionid" prop="unionId">
              <span>{{ form.unionId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在国家" prop="country">
              <span>{{ form.country }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在省份" prop="province">
              <span>{{ form.province }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在城市" prop="city">
              <span>{{ form.city }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="语言" prop="language">
              <template #default="scope">
                <dict-tag :options="wx_use_language" :value="scope.row.language"/>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户昵称" prop="nikeName">
              <span>{{ form.nikeName }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-input v-model="form.gender" placeholder="请输入性别"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登录时间" prop="lastLoginTime">
              <el-date-picker clearable
                              v-model="form.lastLoginTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择最近一次登录时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="IP地址" prop="lastLoginIp">
              <el-input v-model="form.lastLoginIp" placeholder="请输入最近一次登录IP地址"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="open = false">Cancel</el-button>
        <el-button type="primary" @click="open = false">
          Confirm
        </el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup name="CustomerUser">
import {addUser, delUser, getUser, listUser, updateUser} from "@/api/customer/user";

const { proxy } = getCurrentInstance();
const {wx_use_language} = proxy.useDict("wx_use_language");

const userList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {
    nikeName: undefined
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: undefined,
    nikeName:undefined,
    phone:undefined,
    gender:undefined
  },
  rules: {
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询用户管理列表 */
function getList() {
  loading.value = true;
  listUser(queryParams.value).then(response =>{
    console.log(response.rows);
    userList.value = response.rows;
    console.log(userList);
    total.value = response.total;
    loading.value = false;
  });
}
/** 新增按钮操作 */
function handleAdd() {
  console.log("123")
  reset();
  open.value = true;
  title.value = "添加用户";
}


// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    userId: null,
    miniOpenId: null,
    appOpenId: null,
    unionId: null,
    country: null,
    province: null,
    city: null,
    language: null,
    nikeName: null,
    avatarUrl: null,
    phone: null,
    email: null,
    gender: null,
    lastLoginTime: null,
    lastLoginIp: null,
    createTime: null,
    updateTime: null,
  };
  proxy.resetForm("userRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userId)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const userId = row.userId || ids.value
  getUser(userId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改用户";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["userRef"].validate(valid => {
    if (valid) {
      if (form.value.userId !== undefined) {
        updateUser(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addUser(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const userIds = row.userId || ids.value;
  proxy.$modal.confirm('是否确认删除用户管理编号为"' + userIds + '"的数据项？').then(function () {
    return delUser(userIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}
getList();
</script>
