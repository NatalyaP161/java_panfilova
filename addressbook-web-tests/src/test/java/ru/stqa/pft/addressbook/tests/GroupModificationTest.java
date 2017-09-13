package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData("test1", "test2", "test3"));
            app.goTo().returnToGroupPage();
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
         GroupData ModifiedGroup = before.iterator().next();
        GroupData group = new GroupData(ModifiedGroup.getId(),"test1", "test5", "test6");
        app.group().modify(group);
        app.goTo().returnToGroupPage();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(),before.size());

        before.remove(ModifiedGroup);
        before.add(group);
        Assert.assertEquals(before,after);
    }
}
