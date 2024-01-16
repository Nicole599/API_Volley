package com.example.api_volley;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue queque;
    String url = "http://192.168.18.26:8080/api/clientes";
    List<String> datos = new ArrayList<String>();
    ListView lstDatos;
    EditText edtCodigoCliente;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queque = Volley.newRequestQueue(this);

        lstDatos = findViewById(R.id.lstDatos);
        edtCodigoCliente = findViewById(R.id.edtCodigoCliente);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.clear();
                GetApioData(edtCodigoCliente.getText().toString());
            }
        });
    }

    private void GetApioData(String codigoCliente) {
        String urlConCodigo = url + "/" + codigoCliente;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlConCodigo, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                datos.clear();

                try {
                    Cliente cliente = new Cliente();
                    cliente.setId(response.getLong("id"));
                    cliente.setNombre(response.getString("nombre"));
                    cliente.setApellido(response.getString("apellido"));
                    cliente.setEmail(response.getString("email"));
                    cliente.setCreateat(response.getString("createat"));

                    datos.add(cliente.getNombre() + " " +  cliente.getApellido());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, datos);
                    lstDatos.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al parsear la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("VolleyError", error.toString());
            }
        });

        queque.add(request);
    }
}