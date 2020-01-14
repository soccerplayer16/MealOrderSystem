<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--sidebar-->
    <#include "../common/nav.ftl">

    <#--table content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>Category Id</th>
                            <th>Category Name</th>
                            <th>Category Type</th>
                            <th>Create Time</th>
                            <th>Update Time</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoryList as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">Change</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>