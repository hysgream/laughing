# laughing框架
laughing的主要目的是为开发者提供,快速便捷的开发框架和工具，可以大大减少开发中的重复工作。
laughing框架分为一下几个子项目

laughing-base 基础公用项目
   该项目的作用是定义一些公用的jar,定义maven中的一些全局变量，定义私有库位置等等。其他项目都可以集成该项目
   
laughing-pdev-common 通用压缩,打包工具
   该项目有包含一键部署的shell脚本,在项目打包的时候能够将脚本自动打包到项目的bin文件下包含(start.sh , stop.sh restart.sh等等),该工具启动可以指定
develop,test.product三套环境,如:start.sh test表示加载test包下面的所有配置文件,简单的做到各种环境的部署。

laughing-toolkit  通用工具包
   该项目包含了一般开发的大部分工具包,工具类都经过测试和长久使用，能够保证性能和稳定性。工具方面包括,各种加密工具,各种字符串处理工具,json处理工具,
 流处理工具等等涵盖范围广,能解决大部分的业务需求。
 
laughing-framework 快速开发框架
   该项目可以自定义根据业务去修改ibatis的模版，一键化生成从dao层到action层再到view层的整个流程，可以减少70%左右的工作量,开发者只需要对业务处理做
 微改动就可以。后续会支持mybatis,加入各种通用js引擎的功能，敬请期待。
 
laughing-test   测试demo
   这是对于laughing框架整合的测试类,如需了解laughing框架的使用详情，下载该demo就能快死的掌握。

