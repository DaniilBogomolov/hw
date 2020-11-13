<form action="${action}" method="${method}">
    <#if inputs?has_content>
    <#list inputs>
        <#items as input>
            <input name="${input.name}" type="${input.type}" placeholder="${input.placeholder}">
        </#items>
    </#list>
    </#if>
</form>