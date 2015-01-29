package org.argszero.ec2.controller;

import org.argszero.ec2.common.Response;
import org.argszero.ec2.service.SecService;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by shaoaq on 10/23/14.
 */
@Controller
@RequestMapping("/theBigBook")
public class TheBigBookController {

    @Value("${theBigBook.rootDir}")
    private String rootDir;

    @Autowired
    private SecService secService;

    @RequestMapping(value = "/book/{bookName}", method = RequestMethod.PUT)
    @ResponseBody
    public Response createNewBook(@PathVariable String bookName) throws IOException, GitAPIException {
        String user = secService.checkUser();
        File bookRepositoryDir = new File(rootDir, bookName);
        if (!bookRepositoryDir.exists()) {
            FileRepositoryBuilder builder = new FileRepositoryBuilder();
            Repository repository = builder.setGitDir(new File(bookRepositoryDir, ".git"))
                    .readEnvironment() // scan environment GIT_* variables
                    .findGitDir() // scan up the file system tree
                    .build();
            repository.create();
            File file = new File(bookRepositoryDir, "index.txt");
            file.createNewFile();
            Git git = new Git(repository);
            AddCommand add = git.add();
            add.addFilepattern(file.getName()).call();
            CommitCommand commit = git.commit();
            commit.setAuthor(user, user);
            commit.setMessage("initial commit").call();
        }
        return new Response(true, "");
    }

    @RequestMapping(value = "/book/{bookName}.html", method = RequestMethod.GET)
    public String listBranches(@PathVariable String bookName, ModelMap map) throws IOException, GitAPIException {
        map.put("bookName", bookName);

        return "/theBigBook/book.jsp";
    }


    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public String[] listBooks() {
        File[] files = new File(rootDir).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith(".");
            }
        });
        if (files != null) {
            String[] filePaths = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                filePaths[i] = files[i].getPath().replaceAll(rootDir, "");
            }
            return filePaths;
        } else {
            return new String[0];
        }
    }

}