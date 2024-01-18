package com.example.demo;

import com.example.demo.entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/jira")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JiraController {

    @GetMapping("/projects/all")
    public List<Project> getAllProjects() throws UnirestException, JsonProcessingException {
        List<Project> projects = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/api/2/project?expand=description,lead")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .asJson();
        JSONArray proj = response.getBody().getArray();
        for (int i = 0; i < proj.length(); i++) {
            String json = proj.getJSONObject(i).toString();
            Project project = mapper.readValue(json, Project.class);
            projects.add(project);
        }

        return projects;
    }


    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable String id) throws UnirestException, JsonProcessingException {
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/api/2/project/" + id)
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .asJson();
        JSONObject proj = response.getBody().getObject();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(proj.toString(), Project.class);
    }

    @GetMapping("/lead/{id}")
    public Lead getLeadById(@PathVariable String id) throws UnirestException, JsonProcessingException {
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/api/2/user")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .queryString("accountId", id)
                .asJson();

        JSONObject lead = response.getBody().getObject();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(lead.toString(), Lead.class);
    }

    @GetMapping("/project/{id}/board")
    public List<Board> getBoardByProjectId(@PathVariable String id) throws UnirestException, JsonProcessingException {
        List<Board> boards = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/agile/1.0/board")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .queryString("projectKeyOrId", id)
                .asJson();

        JSONArray b = response.getBody().getObject().getJSONArray("values");
        for (int i = 0; i < b.length(); i++) {
            boards.add(mapper.readValue(b.getJSONObject(i).toString(), Board.class));
        }

        return boards;
    }

    @GetMapping("/board/{boardId}/sprint")
    public List<Sprint> getSprintByBoardId(@PathVariable String boardId) throws UnirestException, JsonProcessingException {
        List<Sprint> sprints = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/agile/1.0/board/"+boardId+"/sprint")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .asJson();

        JSONArray b = response.getBody().getObject().getJSONArray("values");
        for (int i = 0; i < b.length(); i++) {
            sprints.add(mapper.readValue(b.getJSONObject(i).toString(), Sprint.class));
        }

        return sprints;

    }

   @GetMapping("/sprint/{sprintId}")
   public Sprint getSprintById(@PathVariable String sprintId) throws UnirestException, JsonProcessingException {
       HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/agile/1.0/sprint/" + sprintId)
               .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
               .header("Accept", "application/json")
               .asJson();

       ObjectMapper mapper = new ObjectMapper();
       return mapper.readValue(response.getBody().getObject().toString(), Sprint.class);
    }

    @GetMapping("/sprint/{sprintId}/issues")
    public List<Issue> getIssuesBySprintId(@PathVariable String sprintId) throws UnirestException, JsonProcessingException {
        List<Issue> issues = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/agile/1.0/sprint/" + sprintId + "/issue")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .asJson();

        JSONArray b = response.getBody().getObject().getJSONArray("issues");
        for (int i = 0; i < b.length(); i++) {
           issues.add(mapper.readValue(b.getJSONObject(i).toString(), Issue.class));
        }

        return issues;

    }

    @PatchMapping("/issue/{issueId}")
    public void updateIssue(@PathVariable String issueId, @RequestBody Issue issue) throws UnirestException {

        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode transition = payload.putObject("transition");
            {
                transition.put("id", issue.getFields().getStatus().getId());
                transition.put("name", issue.getFields().getStatus().getName());
            }


        }

        log.info("payload: {}", payload);

        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        HttpResponse<JsonNode> response = Unirest.post("https://chedli.atlassian.net/rest/api/2/issue/"+issueId+"/transitions")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .asJson();

        System.out.println(response.getBody());
    }

    @PutMapping("/issue/{issueId}/summary")
    public void updateIssueSummary(@PathVariable String issueId, @RequestBody Issue issue) throws UnirestException {

        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode fields = payload.putObject("fields");
            {
                fields.put("summary", issue.getFields().getSummary());
            }
        }

        log.info("payload: {}", payload);

        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        HttpResponse<JsonNode> response = Unirest.put("https://chedli.atlassian.net/rest/api/2/issue/"+issueId)
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .asJson();

        System.out.println(response.getBody());
    }

    @PutMapping("/issue/{issueId}/assignee/{userId}")
    public void assignIssueToUser(@PathVariable String issueId,@PathVariable String userId) throws UnirestException {
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            payload.put("accountId", userId);
        }

            Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
                private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                        = new com.fasterxml.jackson.databind.ObjectMapper();

                public <T> T readValue(String value, Class<T> valueType) {
                    try {
                        return jacksonObjectMapper.readValue(value, valueType);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                public String writeValue(Object value) {
                    try {
                        return jacksonObjectMapper.writeValueAsString(value);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


        HttpResponse<JsonNode> response = Unirest.put("https://chedli.atlassian.net/rest/api/2/issue/"+issueId+"/assignee")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .asJson();

    }


    @GetMapping("/project/{id}/members")
    public List<Actor> getDevelopers(@PathVariable String id ) throws UnirestException, JsonProcessingException {
        List<Actor> members = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<JsonNode> response = Unirest.get("https://chedli.atlassian.net/rest/api/2/project/"+id+"/role/10024")
                .basicAuth("iheb.cherif@talan.com", "ATATT3xFfGF0nOftFzZDIvIWYtaJ4BoxoQ5w600Y3wxijVBshmVNXx6TzVk-CbpLwnH47uffO7RFFyUXU4VZ9Q8JzF7mLHAvfJ8D6J_s7rFzCBLg8p4odrD-5AxhqnCzJBypPKAw34ZQMJlz-fZuuEc44yzs8qWh3gyRT73NxK3_3jYrkon98Xo=F9720582")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        JSONArray actors = response.getBody().getObject().getJSONArray("actors");

        for (int i = 0; i < actors.length(); i++) {
            members.add(mapper.readValue(actors.getJSONObject(i).toString(), Actor.class));
        }

        return members;


    }

}