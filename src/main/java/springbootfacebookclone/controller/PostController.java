package springbootfacebookclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.model.Post;
import springbootfacebookclone.service.PostServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @RequestMapping(value = "/postProcessing", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, HttpServletResponse response,
                          @ModelAttribute("post") Post post) {

        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");

        try {
            Part part = request.getPart("file");

            //path to store image
            String path = "/Users/protek/Desktop/SpringbootFacebookClone/src/main/resources/static/images"+File.separator+post.getImageName();

            InputStream in = part.getInputStream();
            boolean success = uploadFile(in, path);

            if(success){
                if(postService.createPost(person.getId(), post))
                    session.setAttribute("message", "File uploaded successfully");
                else
                    session.setAttribute("message", "Error uploading image to database");
            }else{
                session.setAttribute("message", "error uploading file");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    /**
     * method for reading images to a specific path
     * @param in
     * @param path
     * @return boolean
     */
    public boolean uploadFile(InputStream in, String path){
        boolean test = false;

        try{
            byte[] byt = new byte[in.available()];
            in.read(byt);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            test = true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return test;
    }
}
