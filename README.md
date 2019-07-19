

1.模块
- 用户管理：用户的登录、注册、权限管理、收货地址等等。
- 订单管理：订单的增删改查、购物车功能、支付等。
- 产品管理（药品库）：产品的增删改查、匹配。
- 物流配送：提供物流功能。
- 数据分析：提供对本系统数据分析的功能。
- 桥梁：提供统一路由


## 2. 创建项目的组织结构

- 在drugstore这个项目下创建各个子模块，每个自模块都是一个独立的SpringBoot项目：
    * drugstore-user  用户服务（登陆、注册、权限、收货地址）
    * drugstore-order  订单服务（支付）
    * drugstore-product 产品服务（药品库）
	* drugstore-delivery   物流配送
    * drugstore-analysis  数据分析服务
    * drugstore-api
    本系统的控制层，和以往三层结构中的Controller层的作用一样，都是用作请求调度，只不过在微服务架构中，我们将它抽象成一个单独的系统，可以独立运行。
    * drugstore-common-facade
    它处于本系统的最底层，被所有模块依赖，一些公用的类库都放在这里。
  