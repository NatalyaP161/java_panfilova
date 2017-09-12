package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        List<GroupData>  before = app.group().list();
        GroupData group = new GroupData("test1", "test2", "test3");
        app.group().create(group);
        app.goTo().returnToGroupPage();
        List<GroupData>  after = app.group().list();
        Assert.assertEquals(after.size(),before.size() + 1);

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        after.sort(byId);
        group.setId(after.get(after.size() - 1).getId());
        before.add(group);
        before.sort(byId);
        Assert.assertEquals(before, after);
    }

}
