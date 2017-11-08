package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTest {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("54502fe07e7fed60a7064ec0c10890c4f58702f8");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("NatalyaP161", "java_panfilova")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
