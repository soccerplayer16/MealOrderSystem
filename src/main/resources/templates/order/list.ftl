<html>
    <head>
        <title>
            Seller Product List
        </title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>
                            Order ID
                        </th>
                        <th>
                            Buyer Name
                        </th>
                        <th>
                            Phone Number
                        </th>
                        <th>
                            Address
                        </th>
                        <th>
                            Amount
                        </th>
                        <th>
                            Order Status
                        </th>
                        <th>
                            Payment Status
                        </th>
                        <th>
                            Created Time
                        </th>
                        <th colspan="2">
                            Action
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum()}</td>
                        <td>${orderDTO.getPayStatusEnum()}</td>
                        <td>${orderDTO.createTime} </td>
                        <td>Details</td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().message != "CANCEL">
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">Cancel</a>
                            </#if>
                        </td>
                        <td>Cancel</td>

                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-12 column">
            <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class = "disabled"><a href="#">Prev</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size={$size}">Prev</a></li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentPage == index>
                        <li class = "disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${index}&size={$size}">${index}</a></li>
                    </#if>

                </#list>
                <#if currentPage gte orderDTOPage.getTotalPages()>
                    <li class = "disabled"><a href="#">Next</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size={$size}">Next</a></li>
                </#if>
            </ul>
        </div>
    </div>
    </body>
</html>