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
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户昵称" align="center" prop="nikeName"/>
      <el-table-column label="手机号码" align="center" prop="phone" />
      <el-table-column label="所在国家" align="center" prop="country"/>
      <el-table-column label="所在省份" align="center" prop="province"/>
      <el-table-column label="所在城市" align="center" prop="city"/>
      <el-table-column label="语言" align="center" prop="language">
        <template #default="scope">
          <dict-tag :options="wx_use_language" :value="scope.row.language" />
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center" prop="email"/>
      <el-table-column label="性别" align="center" prop="gender"/>
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
              size="small"
              type="text"
              icon="View"
              @click="viewDetails(scope.row)"
              v-hasPermi="['customer:user:query']"
          >详情
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
            <el-form-item label="头像" prop="avatarUrl">
              <el-avatar shape="square" :size="100" :src="form.avatarUrl"/>
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
            <el-form-item label="使用语言" prop="language">
              <span>{{ form.language }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户昵称" prop="nikeName">
              <span>{{ form.nikeName }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <span>{{ form.phone }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <span>{{ form.email }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <span>{{ form.gender }}</span>
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
            <el-form-item label="注册时间" prop="lastLoginTime">
              <span>{{parseTime(form.createTime)}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最后登录时间" prop="lastLoginTime">
              <span>{{parseTime(form.lastLoginTime)}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最后登录IP" prop="lastLoginIp">
              <span>{{ form.lastLoginIp }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>

  </div>
</template>

<script setup name="CustomerUser">
import {getUser, listUser} from "@/api/customer/user";

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
    userList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
/** 用户详情 */
function viewDetails(row) {
  console.log("viewDetails")
  open.value = true;
  const userId = row.userId || ids.value;
  getUser(userId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "用户详情";
  });
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
/** 导出按钮操作 */
function handleExport() {
  proxy.download("customer/user/export", {
    ...queryParams.value,
  }, `user_${new Date().getTime()}.xlsx`);
}
getList();
</script>
