package com.codecool.lodgingsmanager.service.delete;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.model.Comment;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.DeletableType;

import java.util.ArrayList;
import java.util.List;

public class DeletionService {

    private final BaseService<User> userHandler = Initializer.USER_HANDLER;
    private final BaseService<Lodgings> lodgingsHandler = Initializer.LODGINGS_HANDLER;
    private final BaseService<Comment> commentHandler = Initializer.COMMENT_HANDLER;
    private final BaseService<ToDo> toDoHandler = Initializer.TO_DO_HANDLER;


    public String handleAllDeletionsBy(String parameterName, String stringId) {
        List<String> deletableTypeList = createDeletableTypeList();
        String redirectServletPath;

        if (stringId != null && deletableTypeList.contains(parameterName)) {
            long id = Long.parseLong(stringId);

            DeletableType deletableType = DeletableType.valueOf(parameterName.toUpperCase());
            switch (deletableType) {
                case COMMENT:
                    commentHandler.handleDeletion(id);
                    redirectServletPath = "/index";
                    break;
                case USER:
                    userHandler.handleDeletion(id);
                    redirectServletPath = "/login";
                    break;
                case LODGINGS:
                    lodgingsHandler.handleDeletion(id);
                    redirectServletPath = "/lodgings";
                    break;
                case TODO:
                    toDoHandler.handleDeletion(id);
                    redirectServletPath = "/index";
                    break;
                default:
                    redirectServletPath = "/index";
                    break;
            }
        } else {
            redirectServletPath = "/index";
            // todo: throw some error code to inform the user if the deletion was successful
        }
        return redirectServletPath;
    }

    private List<String> createDeletableTypeList() {
        List<String> deletableTypeList = new ArrayList<>();
        for (DeletableType model : DeletableType.values()) {
            deletableTypeList.add(model.getModelTypeString());
        }
        return deletableTypeList;
    }

}
