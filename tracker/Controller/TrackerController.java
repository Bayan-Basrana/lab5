package com.example.tracker.Controller;

import com.example.tracker.ApiResponse.ApiResponse;
import com.example.tracker.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {
    ArrayList<Project> projects = new ArrayList<>();
@PostMapping("/add")
    public ApiResponse addProject (@RequestBody Project project) {
        projects.add( project );
        return new ApiResponse("Successfully added project");
    }
@GetMapping("/get")
public ArrayList<Project> getProjects() {
    return projects;
}

@PutMapping("/update/{id}")
public ApiResponse updateProject(@PathVariable int id   ,@RequestBody Project project) {
    for(Project p : projects) {
        if(p.getId() == id) {
            p.setTitle(project.getTitle());
            p.setDescription(project.getDescription());
            p.setStatus(project.getStatus());
            p.setCompanyName(project.getCompanyName());
        }
    }return new ApiResponse("Successfully updated project");
}

@DeleteMapping("/delete/{id}")
public ApiResponse deleteProject(@PathVariable int id) {
    projects.removeIf(p -> p.getId() == id);
    return new ApiResponse("Successfully deleted project");
}
@PutMapping("/changeStatus")
public ApiResponse changeStatus () {
    for(Project p : projects) {
        if (p.getStatus().equalsIgnoreCase("not done"))
            p.setStatus("done");
    }return new ApiResponse("Successfully changed status");
}
@GetMapping("/search/{title}")
public Project searchProjects(@PathVariable String title) {
    for(Project p : projects) {
        if(p.getTitle().equalsIgnoreCase(title)) {
            return p;
        }
    }  return null;
}
@GetMapping("/displayByCompanyName/{companyName}")
public ArrayList<Project> displayByCompanyName(@PathVariable String companyName) {
    ArrayList<Project> projectByCompanyName = new ArrayList<>();
    for(Project p : projects) {
        if(p.getCompanyName().equalsIgnoreCase(companyName)) {
            projectByCompanyName.add(p);
        }
    }return projectByCompanyName;
}




}
