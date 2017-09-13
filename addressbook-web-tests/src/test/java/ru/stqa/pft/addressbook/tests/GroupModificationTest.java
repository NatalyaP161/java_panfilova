package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData ModifiedGroup = before.iterator().next();
        GroupData group = new GroupData(ModifiedGroup.getId(),"test1", "test5", "test6");
        app.group().modify(group);
        app.goTo().returnToGroupPage();
        Groups after = app.group().all();
        assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.without(ModifiedGroup).withAdded(group)));
    }
}
