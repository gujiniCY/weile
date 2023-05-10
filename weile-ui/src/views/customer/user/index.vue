<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
            v-model="queryParams.userId"
            placeholder="请输入用户ID"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小程序openId" prop="miniOpenId">
        <el-input
            v-model="queryParams.miniOpenId"
            placeholder="请输入小程序openId"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="AppOpenId" prop="appOpenId">
        <el-input
            v-model="queryParams.appOpenId"
            placeholder="请输入AppOpenId"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="unionid" prop="unionId">
        <el-input
            v-model="queryParams.unionId"
            placeholder="请输入unionid"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在国家" prop="country">
        <el-input
            v-model="queryParams.country"
            placeholder="请输入所在国家"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在省份" prop="province">
        <el-input
            v-model="queryParams.province"
            placeholder="请输入所在省份"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在城市" prop="city">
        <el-input
            v-model="queryParams.city"
            placeholder="请输入所在城市"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文" prop="language">
        <el-input
            v-model="queryParams.language"
            placeholder="请输入所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nikeName">
        <el-input
            v-model="queryParams.nikeName"
            placeholder="请输入用户昵称"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="头像地址" prop="avatarUrl">
        <el-input
            v-model="queryParams.avatarUrl"
            placeholder="请输入头像地址"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input
            v-model="queryParams.phone"
            placeholder="请输入手机号码"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
            v-model="queryParams.email"
            placeholder="请输入邮箱"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别 0：未知、1：男、2：女" prop="gender">
        <el-input
            v-model="queryParams.gender"
            placeholder="请输入性别 0：未知、1：男、2：女"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最近一次登录时间" prop="lastLoginTime">
        <el-date-picker clearable
                        v-model="queryParams.lastLoginTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择最近一次登录时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最近一次登录IP地址" prop="lastLoginIp">
        <el-input
            v-model="queryParams.lastLoginIp"
            placeholder="请输入最近一次登录IP地址"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['customer:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['customer:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['customer:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['customer:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="小程序openId" align="center" prop="miniOpenId" />
      <el-table-column label="AppOpenId" align="center" prop="appOpenId" />
      <el-table-column label="unionid" align="center" prop="unionId" />
      <el-table-column label="所在国家" align="center" prop="country" />
      <el-table-column label="所在省份" align="center" prop="province" />
      <el-table-column label="所在城市" align="center" prop="city" />
      <el-table-column label="所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文" align="center" prop="language" />
      <el-table-column label="用户昵称" align="center" prop="nikeName" />
      <el-table-column label="头像地址" align="center" prop="avatarUrl" />
      <el-table-column label="手机号码" align="center" prop="phone" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="性别 0：未知、1：男、2：女" align="center" prop="gender" />
      <el-table-column label="最近一次登录时间" align="center" prop="lastLoginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastLoginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最近一次登录IP地址" align="center" prop="lastLoginIp" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['customer:user:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['customer:user:remove']"
          >删除</el-button>
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

    <!-- 添加或修改用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="小程序openId" prop="miniOpenId">
          <el-input v-model="form.miniOpenId" placeholder="请输入小程序openId" />
        </el-form-item>
        <el-form-item label="AppOpenId" prop="appOpenId">
          <el-input v-model="form.appOpenId" placeholder="请输入AppOpenId" />
        </el-form-item>
        <el-form-item label="unionid" prop="unionId">
          <el-input v-model="form.unionId" placeholder="请输入unionid" />
        </el-form-item>
        <el-form-item label="所在国家" prop="country">
          <el-input v-model="form.country" placeholder="请输入所在国家" />
        </el-form-item>
        <el-form-item label="所在省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入所在省份" />
        </el-form-item>
        <el-form-item label="所在城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入所在城市" />
        </el-form-item>
        <el-form-item label="所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文" prop="language">
          <el-input v-model="form.language" placeholder="请输入所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="nikeName">
          <el-input v-model="form.nikeName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="头像地址" prop="avatarUrl">
          <el-input v-model="form.avatarUrl" placeholder="请输入头像地址" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="性别 0：未知、1：男、2：女" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别 0：未知、1：男、2：女" />
        </el-form-item>
        <el-form-item label="最近一次登录时间" prop="lastLoginTime">
          <el-date-picker clearable
                          v-model="form.lastLoginTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择最近一次登录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最近一次登录IP地址" prop="lastLoginIp">
          <el-input v-model="form.lastLoginIp" placeholder="请输入最近一次登录IP地址" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/customer/user";

export default {
  name: "User",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户管理表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户管理列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
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
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getUser(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != null) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除用户管理编号为"' + userIds + '"的数据项？').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
