package com.example.uasrpl.Tab;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uasrpl.Config.AdapterEvent;
import com.example.uasrpl.Config.Link;
import com.example.uasrpl.Dto.EventDto;
import com.example.uasrpl.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Webinar extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tampil = inflater.inflate(R.layout.fragment_webinar, container, false);
        recyclerView = tampil.findViewById(R.id.list_event);
        api(getContext());
        return tampil;
    }

    private void api(Context ct) {
        Link link = new Link();
        List<EventDto> data = new ArrayList<>();
        RequestQueue api = Volley.newRequestQueue(ct);
        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET, link.getEvent(), null,  new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        EventDto tmp = new EventDto();
                        tmp.setIdEvent(response.getJSONObject(i).getString("id_event"));
                        tmp.setJudul(response.getJSONObject(i).getString("judul"));
                        tmp.setDeskripsi(response.getJSONObject(i).getString("deskripsi"));
                        tmp.setPembicara(response.getJSONObject(i).getString("pembicara"));
                        tmp.setJamMulai(response.getJSONObject(i).getString("jam_mulai"));
                        tmp.setJamSelesai(response.getJSONObject(i).getString("jam_selesai"));
                        tmp.setGambar(response.getJSONObject(i).getString("gambar"));
                        data.add(tmp);
                    }
                } catch (JSONException e) {
                    Log.e("Error", e.toString());
                }
                AdapterEvent tampilan = new AdapterEvent(data, ct);
                recyclerView.setAdapter(tampilan);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("json event", error.toString());
            }
        });
        api.add(json);
    }
}