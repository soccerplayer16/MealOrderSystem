<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--        Side bar -->
    <#include "../common/nav.ftl">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Total Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>

<#--        Order detail table -->
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                    <tr>
                        <td>${orderDetail.productId}</td>
                        <td>${orderDetail.productName}</td>
                        <td>${orderDetail.productPrice}</td>
                        <td>${orderDetail.productQuantity}</td>
                        <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>

<#--        Action-->
        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().message = "NEW">
                <a href = "/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">Finish Order</a>
                <a href = "/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">Cancel Order</a>
            </#if>

        </div>
    </div>
</div>
</body>
</html>