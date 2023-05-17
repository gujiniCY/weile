<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="快递公司名称" prop="courierCompanyName">
        <el-input
            v-model="queryParams.courierCompanyName"
            clearable
            placeholder="请输入快递公司名称"
            style="width: 200px"
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="快递公司代码" prop="courierCompanyCode">
        <el-input
            v-model="queryParams.courierCompanyCode"
            clearable
            placeholder="请输入快递公司代码"
            style="width: 200px"
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="状态" clearable style="width: 200px">
          <el-option
              v-for="dict in logistics_courier_company_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
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
            @click="handleAdd"
            v-hasPermi="['logistics:courierCompany:add']"
        >新增
        </el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['logistics:courierCompany:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['logistics:courierCompany:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['logistics:courierCompany:export']"
        >导出</el-button>
      </el-col>

    </el-row>

    <el-table v-loading="loading" :data="courierCompanyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id"/>
      <el-table-column label="快递公司名称" align="center" prop="courierCompanyName"/>
      <el-table-column label="快递公司编码" align="center" prop="courierCompanyCode"/>
      <el-table-column label="网址" align="center" prop="url"/>
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="logistics_courier_company_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['logistics:courierCompany:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['logistics:courierCompany:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />
    <!-- 添加或修改岗位对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="courierCompanyRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="快递公司名称" prop="courierCompanyName">
          <el-input v-model="form.courierCompanyName" placeholder="请输入公司名称"/>
        </el-form-item>

        <el-form-item label="快递公司编码" prop="courierCompanyCode">
          <el-input v-model="form.courierCompanyCode" placeholder="请输入编码名称" />
        </el-form-item>
        <el-form-item label="网址" prop="url">
          <el-input v-model="form.url" placeholder="请输入网址" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
                v-for="dict in logistics_courier_company_status"
                :key="dict.value"
                :label="dict.value"
            >{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>

</template>

<script setup name="logisticsCourierCompany">
import {addCourierCompany, listCourierCompany,getCourierCompany, updateCourierCompany,delCourierCompany} from "@/api/logistics/courierCompany";

const {proxy} = getCurrentInstance();

const {logistics_courier_company_status} = proxy.useDict("logistics_courier_company_status");

const courierCompanyList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const total = ref(0);
const title = ref("");
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);


const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    courierCompanyName: undefined,
    courierCompanyCode: undefined,
    status: undefined
  },
  rules: {
    courierCompanyName: [{required: true, message: "快递公司名称不能为空", trigger: "blur"}],
    courierCompanyCode: [{required: true, message: "快递公司编码不能为空", trigger: "blur"}],
  }
});

const {queryParams, form, rules} = toRefs(data);

/** 查询岗位列表 */
function getList() {
  loading.value = true;
  listCourierCompany(queryParams.value).then(response => {
    courierCompanyList.value = response.rows;
    console.log(response.rows)
    total.value = response.total;
    loading.value = false;
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 表单重置 */
function reset() {
  form.value = {
    id: undefined,
    courierCompanyName: undefined,
    courierCompanyCode: undefined,
    url: undefined,
    status: "0",
    remark: undefined
  };
  proxy.resetForm("courierCompanyRef");
}

/**添加弹出框**/
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加快递公司";
}

/**
 * 提交按钮
 */
function submitForm() {
  proxy.$refs["courierCompanyRef"].validate(valid => {
    if (valid) {
      if (form.value.id != undefined) {
        updateCourierCompany(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");

          open.value = false;
          getList();
        })
      } else {
        addCourierCompany(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        })
      }
    }
  })
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const id = row.id || ids.value;
  getCourierCompany(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改快递公司";
  });
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}

/** 删除按钮操作 */
function handleDelete(row) {
  const delIds = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除id为"' + delIds + '"的数据项？').then(function() {
    return delCourierCompany(delIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("logistics/courierCompany/export", {
    ...queryParams.value
  }, `post_${new Date().getTime()}.xlsx`);
}
getList();

</script>
