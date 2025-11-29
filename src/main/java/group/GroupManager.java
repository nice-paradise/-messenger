package group;

import user.User;

public class GroupManager {
    private Group[] groups = new Group[10];
    private int groupCount = 0;

    public Group createGroup(String name, User creator) {
        if (groupCount < groups.length) {
            Group group = new Group(name);
            group.addUser(creator);
            groups[groupCount++] = group;
            System.out.println("Группа '" + name + "' создана!");
            return group;
        } else {
            System.out.println("Достигнут лимит групп!");
            return null;
        }
    }

    public Group findGroup(String name) {
        for (int i = 0; i < groupCount; i++) {
            if (groups[i].getName().equals(name)) {
                return groups[i];
            }
        }
        return null;
    }

    public void showAllGroups() {
        System.out.println("\n=== ВСЕ ГРУППЫ ===");
        if (groupCount == 0) {
            System.out.println("Групп нет");
        } else {
            for (int i = 0; i < groupCount; i++) {
                System.out.println((i+1) + ". " + groups[i].getName() +
                        " (участников: " + groups[i].getMemberCount() + "/" + groups[i].getMaxMembers() + ")");
            }
        }
    }

    public void showUserGroups(User user) {
        System.out.println("\n=== МОИ ГРУППЫ ===");
        boolean found = false;
        for (int i = 0; i < groupCount; i++) {
            Group group = groups[i];
            for (int j = 0; j < group.getMemberCount(); j++) {
                if (group.members[j].getLogin().equals(user.getLogin())) {
                    System.out.println((i+1) + ". " + group.getName());
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Вы не состоите в группах");
        }
    }
}