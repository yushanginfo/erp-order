[#ftl]
[@b.head/]
[@b.toolbar title="品号类型"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">代码</td>
    <td class="content">${materialType.code}</td>
  </tr>
  <tr>
    <td class="title" width="20%">名称</td>
    <td class="content">${materialType.name}</td>
  </tr>
</table>

[@b.foot/]
