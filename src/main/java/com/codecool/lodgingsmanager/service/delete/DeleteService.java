package com.codecool.lodgingsmanager.service.delete;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.model.Comment;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.DeletableType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DeleteService {

    private final BaseService<User> userHandler = Initializer.USER_HANDLER;
    private final BaseService<Lodgings> lodgingsHandler = Initializer.LODGINGS_HANDLER;
    private final BaseService<Comment> commentHandler = Initializer.COMMENT_HANDLER;
    private final BaseService<ToDo> toDoHandler = Initializer.TO_DO_HANDLER;


    public void handleAllDeletes(HttpServletRequest request, HttpServletResponse response, Enumeration<String> parameterNames) throws IOException {
        List<String> deletableTypeList = createDeletableTypeList();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (deletableTypeList.contains(parameterName)) {
                String stringId = request.getParameter(parameterName);
                long id = Long.parseLong(stringId);
                DeletableType deletableType = DeletableType.valueOf(parameterName.toUpperCase());
                switch (deletableType) {
                    case COMMENT:
                        commentHandler.handleDelete(id);
                        break;
                    case USER:
                        userHandler.handleDelete(id);
                        response.sendRedirect("/login");
                        break;
                    case LODGINGS:
                        lodgingsHandler.handleDelete(id);
                        response.sendRedirect("/lodgings");
                        break;
                    case TODO:
                        toDoHandler.handleDelete(id);
                        break;
                }
            }
        }
    }

    private List<String> createDeletableTypeList() {
        List<String> deletableTypeList = new ArrayList<>();
        for (DeletableType model : DeletableType.values()) {
            deletableTypeList.add(model.getModelTypeString());
        }
        return deletableTypeList;
    }


}
