package com.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MvcResult;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.app.payloads.*;
import com.app.models.*;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AddControllerTest {

	@Mock
	private SongPayload songPayload;
	
	@Mock
	private Song song;
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public Song testAddSongManual() throws Exception {
        String songQuery = "water from wine";
        String artistQuery = "amaarae";
        String userId = "32";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/add/manual-song-assisted/{songQuery}/{artistQuery}/{userId}",
                songQuery, artistQuery, userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        String responseBody = result.getResponse().getContentAsString();
        Song song = objectMapper.readValue(responseBody, Song.class);
        
        return song;
    }

    @Test
    public void testAddSongAccepted() throws Exception {
    	Song song = testAddSongManual();
        String userId = "32";
        
        SongPayload songPayload = new SongPayload(song);

        mockMvc.perform(MockMvcRequestBuilders.post("/add/manual-song-accepted/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(songPayload)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void testAddSongUnique() throws Exception {
//        String userId = "33";
//        String songQuery = "water from wine";
//        List<String> artistQuery = new ArrayList<String>();
//        artistQuery.add("amaarae");
//        SongPayload songPayload = new SongPayload(songQuery, artistQuery);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add/manual-song-unique/{userId}", userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(songPayload)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}
