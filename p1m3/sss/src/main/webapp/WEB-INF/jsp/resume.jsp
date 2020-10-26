<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JPA操作页</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>

<div id="vue_det">

    <div class="app-container">

        <div class="filter-container">
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
                添加
            </el-button>
        </div>
        <el-table
                :key="tableKey"
                v-loading="listLoading"
                :data="list"
                border
                fit
                highlight-current-row
                style="width: 100%;"
        >
            <el-table-column label="序号" prop="id" sortable align="center" >
                <template slot-scope="{row}">
                    <span>{{ row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="地址" min-width="100px" align="center">
                <template slot-scope="{row}">
                    <span>{{ row.address }}</span>
                </template>
            </el-table-column>

            <el-table-column label="姓名" align="center" min-width="120px">
                <template slot-scope="{row}">
                    <span >{{ row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column label="电话" min-width="100px" align="center">
                <template slot-scope="{row}">
                    <span>{{ row.phone }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" align="center" min-width="100px" class-name="small-padding fixed-width">
                <template slot-scope="{row}">
                    <el-button type="primary" size="mini" @click="handleUpdate(row)"> 编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>


    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" top="5vh" :width="dialogWidth">
        <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" size="mini" label-width="100px" style="margin-left:50px;">

            <el-form-item label="地址" prop="fileSaveTypeCode">
                <el-input v-model="temp.address" clearable type="text" placeholder="请输入地址" />
            </el-form-item>
            <el-form-item label="姓名" prop="fileSaveTypeName">
                <el-input v-model="temp.name" clearable type="text" placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="temp.phone" clearable  type="text" placeholder="请输入电话" />
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">
                返回
            </el-button>
            <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
                确认
            </el-button>
        </div>
    </el-dialog>
</div>

</body>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
    var vm = new Vue({
        el: '#vue_det',
        data: {
            tableKey: 0,
            list: null,
            listLoading: true,
            temp: {
                id: undefined,
                name: '',
                address: '',
                phone: '',
            },
            dialogWidth: '35%',
            dialogFormVisible: false,
            dialogStatus: '',
            textMap: {
                update: '修改',
                create: '添加'
            },
            rules: {
                address: [{ required: true, message: '请填写地址', trigger: 'blur' }],
                phone: [{ required: true, message: '请填写电话', trigger: 'blur' }],
                name: [{  required: true, message: '请填写姓名', trigger: 'blur' }]
            },
            downloadLoading: false
        },
        // 初始化获取数据列表
        created() {
            this.getList()
        },
        mounted() {
            window.onresize = () => {
                return (() => {
                    this.setDialogWidth()
                })()
            }
        },
        methods: {
            // 调整窗口宽度
            setDialogWidth() {
                // console.log(document.body.clientWidth)
                var val = document.body.clientWidth
                const def = 1600 // 默认宽度
                if (val < def) {
                    this.dialogWidth = 600 + 'px'
                } else {
                    this.dialogWidth = '35%'
                }
            },

            // 有加载圈的加载数据列表
            getList() {
                this.listLoading = true
                axios({
                    method: 'get',
                    url: '/resume/list',
                    params: {}
                }).then(response => {
                    this.list = response.data.data
                    // Just to simulate the time of the request
                    setTimeout(() => {
                        this.listLoading = false
                    }, 1 * 500)
                })
            },

            // 立即刷新数据列表
            refreshList() {
                axios({
                    method: 'get',
                    url: '/resume/list',
                    params: {}
                }).then(response => {
                    this.list = response.data.data
                })
            },
            // 重置temp实体类变量属性
            resetTemp() {
                this.temp = {
                    id: undefined,
                    name: '',
                    address: '',
                    phone: '',
                }
            },
            // 监听create dialog事件
            handleCreate() {
                this.resetTemp()
                this.dialogStatus = 'create'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            // 添加操作
            createData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        axios({
                            method: 'post',
                            url: '/resume/add',
                            data: this.temp
                        }).then(() => {
                            this.refreshList()
                            // this.list.unshift(this.temp)
                            this.dialogFormVisible = false
                            this.$notify({
                                title: 'Success',
                                message: '添加成功',
                                type: 'success',
                                duration: 2000
                            })
                        })
                    }
                })
            },
            // 监听修改 update dialog事件
            handleUpdate(row) {
                this.temp = Object.assign({}, row) // copy obj
                // this.temp.timestamp = new Date(this.temp.timestamp)
                this.dialogStatus = 'update'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            // 修改操作
            updateData() {
                axios({
                    method: 'post',
                    url: '/resume/edit',
                    data: this.temp
                }).then(() => {
                    this.refreshList()
                    // this.list.unshift(this.temp)
                    this.dialogFormVisible = false
                    this.$notify({
                        title: 'Success',
                        message: '修改成功',
                        type: 'success',
                        duration: 2000
                    })
                })
            },

            // 监听删除dialog事件
            handleDelete(row) {
                this.temp = Object.assign({}, row)
                this.$confirm('您确定要删除该数据吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios({
                        method: 'post',
                        url: '/resume/delete',
                        data: this.temp
                    }).then(() => {
                        this.refreshList()
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            }
        }
    })
</script>
</html>

