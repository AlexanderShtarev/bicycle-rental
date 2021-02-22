<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="new.product"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/controller"/>" role="form" method="POST">
                    <input type="hidden" name="command" value="add_product">
                    <div class="col-xs-12">
                        <p class="lead"></p>
                        <div class="form-group">
                            <label for="name"><fmt:message key="label.name"/></label>
                            <div class="controls">
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                        </div>


                        <div class="row">
                            <div class="controls col-xs-12">
                                <label for="price"><fmt:message key="product.price"/>:</label>
                                <div class="controls">
                                    <input min=1 minlength="1" type="number" class="form-control" id="price"
                                           name="price"
                                           pattern="\d+(\.\d+)?" required>
                                </div>
                            </div>
                        </div>

                        <div class="btn-group">
                            <label for="categories"><fmt:message key="main.category"/>:</label>
                            <select class="form-control" name="categoryId" id="categories" required>
                                <option value=""><fmt:message key="main.category"/></option>
                                <%--<c:forEach var="category" items="${categories}">--%>
                                <%-- <li>
                                     <input type="checkbox" name="categories"
                                            value="${category.id}">${category.name}
                                 </li>--%>
                                <%-- </c:forEach>--%>
                            </select>
                        </div>
                        <div class="btn-group">
                            <label for="manufacturers"><fmt:message key="main.manufacturer"/>:</label>
                            <select class="form-control" name="manufacturerId" id="manufacturers" required>
                                <option value=""><fmt:message key="main.manufacturer"/></option>
                                <%-- <c:forEach var="manufacturer" items="${manufacturers}">--%>
                                <%--     <li>
                                         <input type="checkbox" name="manufacturers"
                                                value="${manufacturer.id}">${manufacturer.name}
                                     </li>--%>
                                <%-- </c:forEach>--%>
                            </select>
                        </div>

                        <div class="row">
                            <div class="controls col-xs-12">
                                <label for="description"><fmt:message key="main.description"/>:</label>
                                <div class="controls">
                                    <input type="text" class="form-control" id="description" name="description"
                                           required>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="main.close"/></button>
                        <input type="submit" class="btn btn-primary" value="<fmt:message key="add.product"/>">
                    </div>
                </form>

            </div>

        </div>
    </div>
</div>
