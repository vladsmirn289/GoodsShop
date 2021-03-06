<#import "../parts/common.ftl" as c>
<#import "../parts/customMessage.ftl" as m>
<#import "../parts/pager.ftl" as p>
<#include "../parts/security.ftl">

<@c.commonPage>
    <#if items.content?size == 0>
        <@m.customMessage "Предметов, удовлетворяющих данному запросу, не найдено!"/>
    <#else>
        <@p.pager url items/>
    </#if>

    <div class="row row-cols-1 row-cols-md-2" id="itemsBlock">
        <#list items.content as item>
            <div class="col-md-6">
                <div class="card mb-3 bg-secondary border-dark text-white">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                            <img src="/images/${item.image}" class="card-img" alt="${item.name}"/>
                        </div>
                        <div class="col-md-8 text-center">
                            <div class="card-body">
                                <h4 class="card-title"><a href="/item/${item.id}">${item.name}</a></h4>
                                <p class="card-text">
                                    <#if (item.description?length >= 250)>
                                        ${item.description?substring(0, 250)}...
                                    <#else>
                                        ${item.description}
                                    </#if>
                                </p>
                                <hr/>
                                <p class="card-text"><small>${item.price} руб.</small></p>
                                <#if isAdmin>
                                    <p class="card-text"><a href="/admin/updateItem/${item.id}">Изменить</a></p>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#if items.content?size != 0>
        <@p.pager url items/>
    </#if>
</@c.commonPage>

