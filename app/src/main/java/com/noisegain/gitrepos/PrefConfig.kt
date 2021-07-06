package com.noisegain.gitrepos

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.*
import java.io.StringReader
import java.net.URL

class PrefConfig {

    private val parser = Klaxon()
    fun writePref(fav: MutableSet<String>) {
        //val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val list = parser.toJsonString(fav.toList())
        //println(list)
        //with(pref.edit()) {
        //    putString("fav", list)
        //    apply()
        //}
    }
    fun readPref(): MutableSet<String> {
        //val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val json = pref.getString("fav", "")
        //val res = parser.parse<List<String>>(json?:"")
        //return res?.toMutableSet()?:mutableSetOf()
        return mutableSetOf("User1")
    }

    fun getUser(name: String): User {
        val req = """[
            {
                "id": 271291512,
                "node_id": "MDEwOlJlcG9zaXRvcnkyNzEyOTE1MTI=",
                "name": "currency-converter",
                "full_name": "noisegain/currency-converter",
                "private": false,
                "owner": {
                "login": "noisegain",
                "id": 66730966,
                "node_id": "MDQ6VXNlcjY2NzMwOTY2",
                "avatar_url": "https://avatars.githubusercontent.com/u/66730966?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/noisegain",
                "html_url": "https://github.com/noisegain",
                "followers_url": "https://api.github.com/users/noisegain/followers",
                "following_url": "https://api.github.com/users/noisegain/following{/other_user}",
                "gists_url": "https://api.github.com/users/noisegain/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/noisegain/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/noisegain/subscriptions",
                "organizations_url": "https://api.github.com/users/noisegain/orgs",
                "repos_url": "https://api.github.com/users/noisegain/repos",
                "events_url": "https://api.github.com/users/noisegain/events{/privacy}",
                "received_events_url": "https://api.github.com/users/noisegain/received_events",
                "type": "User",
                "site_admin": false
            },
                "html_url": "https://github.com/noisegain/currency-converter",
                "description": null,
                "fork": false,
                "url": "https://api.github.com/repos/noisegain/currency-converter",
                "forks_url": "https://api.github.com/repos/noisegain/currency-converter/forks",
                "keys_url": "https://api.github.com/repos/noisegain/currency-converter/keys{/key_id}",
                "collaborators_url": "https://api.github.com/repos/noisegain/currency-converter/collaborators{/collaborator}",
                "teams_url": "https://api.github.com/repos/noisegain/currency-converter/teams",
                "hooks_url": "https://api.github.com/repos/noisegain/currency-converter/hooks",
                "issue_events_url": "https://api.github.com/repos/noisegain/currency-converter/issues/events{/number}",
                "events_url": "https://api.github.com/repos/noisegain/currency-converter/events",
                "assignees_url": "https://api.github.com/repos/noisegain/currency-converter/assignees{/user}",
                "branches_url": "https://api.github.com/repos/noisegain/currency-converter/branches{/branch}",
                "tags_url": "https://api.github.com/repos/noisegain/currency-converter/tags",
                "blobs_url": "https://api.github.com/repos/noisegain/currency-converter/git/blobs{/sha}",
                "git_tags_url": "https://api.github.com/repos/noisegain/currency-converter/git/tags{/sha}",
                "git_refs_url": "https://api.github.com/repos/noisegain/currency-converter/git/refs{/sha}",
                "trees_url": "https://api.github.com/repos/noisegain/currency-converter/git/trees{/sha}",
                "statuses_url": "https://api.github.com/repos/noisegain/currency-converter/statuses/{sha}",
                "languages_url": "https://api.github.com/repos/noisegain/currency-converter/languages",
                "stargazers_url": "https://api.github.com/repos/noisegain/currency-converter/stargazers",
                "contributors_url": "https://api.github.com/repos/noisegain/currency-converter/contributors",
                "subscribers_url": "https://api.github.com/repos/noisegain/currency-converter/subscribers",
                "subscription_url": "https://api.github.com/repos/noisegain/currency-converter/subscription",
                "commits_url": "https://api.github.com/repos/noisegain/currency-converter/commits{/sha}",
                "git_commits_url": "https://api.github.com/repos/noisegain/currency-converter/git/commits{/sha}",
                "comments_url": "https://api.github.com/repos/noisegain/currency-converter/comments{/number}",
                "issue_comment_url": "https://api.github.com/repos/noisegain/currency-converter/issues/comments{/number}",
                "contents_url": "https://api.github.com/repos/noisegain/currency-converter/contents/{+path}",
                "compare_url": "https://api.github.com/repos/noisegain/currency-converter/compare/{base}...{head}",
                "merges_url": "https://api.github.com/repos/noisegain/currency-converter/merges",
                "archive_url": "https://api.github.com/repos/noisegain/currency-converter/{archive_format}{/ref}",
                "downloads_url": "https://api.github.com/repos/noisegain/currency-converter/downloads",
                "issues_url": "https://api.github.com/repos/noisegain/currency-converter/issues{/number}",
                "pulls_url": "https://api.github.com/repos/noisegain/currency-converter/pulls{/number}",
                "milestones_url": "https://api.github.com/repos/noisegain/currency-converter/milestones{/number}",
                "notifications_url": "https://api.github.com/repos/noisegain/currency-converter/notifications{?since,all,participating}",
                "labels_url": "https://api.github.com/repos/noisegain/currency-converter/labels{/name}",
                "releases_url": "https://api.github.com/repos/noisegain/currency-converter/releases{/id}",
                "deployments_url": "https://api.github.com/repos/noisegain/currency-converter/deployments",
                "created_at": "2020-06-10T13:58:02Z",
                "updated_at": "2020-06-10T14:16:41Z",
                "pushed_at": "2020-06-10T14:16:39Z",
                "git_url": "git://github.com/noisegain/currency-converter.git",
                "ssh_url": "git@github.com:noisegain/currency-converter.git",
                "clone_url": "https://github.com/noisegain/currency-converter.git",
                "svn_url": "https://github.com/noisegain/currency-converter",
                "homepage": null,
                "size": 19,
                "stargazers_count": 0,
                "watchers_count": 0,
                "language": "Swift",
                "has_issues": true,
                "has_projects": true,
                "has_downloads": true,
                "has_wiki": true,
                "has_pages": false,
                "forks_count": 0,
                "mirror_url": null,
                "archived": false,
                "disabled": false,
                "open_issues_count": 0,
                "license": null,
                "forks": 0,
                "open_issues": 0,
                "watchers": 0,
                "default_branch": "master"
            },
            {
                "id": 382813094,
                "node_id": "MDEwOlJlcG9zaXRvcnkzODI4MTMwOTQ=",
                "name": "GitRepos",
                "full_name": "noisegain/GitRepos",
                "private": false,
                "owner": {
                "login": "noisegain",
                "id": 66730966,
                "node_id": "MDQ6VXNlcjY2NzMwOTY2",
                "avatar_url": "https://avatars.githubusercontent.com/u/66730966?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/noisegain",
                "html_url": "https://github.com/noisegain",
                "followers_url": "https://api.github.com/users/noisegain/followers",
                "following_url": "https://api.github.com/users/noisegain/following{/other_user}",
                "gists_url": "https://api.github.com/users/noisegain/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/noisegain/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/noisegain/subscriptions",
                "organizations_url": "https://api.github.com/users/noisegain/orgs",
                "repos_url": "https://api.github.com/users/noisegain/repos",
                "events_url": "https://api.github.com/users/noisegain/events{/privacy}",
                "received_events_url": "https://api.github.com/users/noisegain/received_events",
                "type": "User",
                "site_admin": false
            },
                "html_url": "https://github.com/noisegain/GitRepos",
                "description": null,
                "fork": false,
                "url": "https://api.github.com/repos/noisegain/GitRepos",
                "forks_url": "https://api.github.com/repos/noisegain/GitRepos/forks",
                "keys_url": "https://api.github.com/repos/noisegain/GitRepos/keys{/key_id}",
                "collaborators_url": "https://api.github.com/repos/noisegain/GitRepos/collaborators{/collaborator}",
                "teams_url": "https://api.github.com/repos/noisegain/GitRepos/teams",
                "hooks_url": "https://api.github.com/repos/noisegain/GitRepos/hooks",
                "issue_events_url": "https://api.github.com/repos/noisegain/GitRepos/issues/events{/number}",
                "events_url": "https://api.github.com/repos/noisegain/GitRepos/events",
                "assignees_url": "https://api.github.com/repos/noisegain/GitRepos/assignees{/user}",
                "branches_url": "https://api.github.com/repos/noisegain/GitRepos/branches{/branch}",
                "tags_url": "https://api.github.com/repos/noisegain/GitRepos/tags",
                "blobs_url": "https://api.github.com/repos/noisegain/GitRepos/git/blobs{/sha}",
                "git_tags_url": "https://api.github.com/repos/noisegain/GitRepos/git/tags{/sha}",
                "git_refs_url": "https://api.github.com/repos/noisegain/GitRepos/git/refs{/sha}",
                "trees_url": "https://api.github.com/repos/noisegain/GitRepos/git/trees{/sha}",
                "statuses_url": "https://api.github.com/repos/noisegain/GitRepos/statuses/{sha}",
                "languages_url": "https://api.github.com/repos/noisegain/GitRepos/languages",
                "stargazers_url": "https://api.github.com/repos/noisegain/GitRepos/stargazers",
                "contributors_url": "https://api.github.com/repos/noisegain/GitRepos/contributors",
                "subscribers_url": "https://api.github.com/repos/noisegain/GitRepos/subscribers",
                "subscription_url": "https://api.github.com/repos/noisegain/GitRepos/subscription",
                "commits_url": "https://api.github.com/repos/noisegain/GitRepos/commits{/sha}",
                "git_commits_url": "https://api.github.com/repos/noisegain/GitRepos/git/commits{/sha}",
                "comments_url": "https://api.github.com/repos/noisegain/GitRepos/comments{/number}",
                "issue_comment_url": "https://api.github.com/repos/noisegain/GitRepos/issues/comments{/number}",
                "contents_url": "https://api.github.com/repos/noisegain/GitRepos/contents/{+path}",
                "compare_url": "https://api.github.com/repos/noisegain/GitRepos/compare/{base}...{head}",
                "merges_url": "https://api.github.com/repos/noisegain/GitRepos/merges",
                "archive_url": "https://api.github.com/repos/noisegain/GitRepos/{archive_format}{/ref}",
                "downloads_url": "https://api.github.com/repos/noisegain/GitRepos/downloads",
                "issues_url": "https://api.github.com/repos/noisegain/GitRepos/issues{/number}",
                "pulls_url": "https://api.github.com/repos/noisegain/GitRepos/pulls{/number}",
                "milestones_url": "https://api.github.com/repos/noisegain/GitRepos/milestones{/number}",
                "notifications_url": "https://api.github.com/repos/noisegain/GitRepos/notifications{?since,all,participating}",
                "labels_url": "https://api.github.com/repos/noisegain/GitRepos/labels{/name}",
                "releases_url": "https://api.github.com/repos/noisegain/GitRepos/releases{/id}",
                "deployments_url": "https://api.github.com/repos/noisegain/GitRepos/deployments",
                "created_at": "2021-07-04T09:34:30Z",
                "updated_at": "2021-07-04T09:34:39Z",
                "pushed_at": "2021-07-04T09:34:37Z",
                "git_url": "git://github.com/noisegain/GitRepos.git",
                "ssh_url": "git@github.com:noisegain/GitRepos.git",
                "clone_url": "https://github.com/noisegain/GitRepos.git",
                "svn_url": "https://github.com/noisegain/GitRepos",
                "homepage": null,
                "size": 134,
                "stargazers_count": 0,
                "watchers_count": 0,
                "language": "Kotlin",
                "has_issues": true,
                "has_projects": true,
                "has_downloads": true,
                "has_wiki": true,
                "has_pages": false,
                "forks_count": 0,
                "mirror_url": null,
                "archived": false,
                "disabled": false,
                "open_issues_count": 0,
                "license": null,
                "forks": 0,
                "open_issues": 0,
                "watchers": 0,
                "default_branch": "master"
            }
        ]"""
            //URL("https://api.github.com/users/$name/repos").readText()
        println(req)
        val parsed = parser.parseJsonObject(StringReader(req))
        println(parsed)
        return User("AAA", arrayListOf())
    }
}