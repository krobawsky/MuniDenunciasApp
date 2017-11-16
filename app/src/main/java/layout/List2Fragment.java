package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ricardo.munidenunciasapp.R;
import com.ricardo.munidenunciasapp.adapter.DenunciasAdapter;
import com.ricardo.munidenunciasapp.models.Denuncia;
import com.ricardo.munidenunciasapp.service.ApiService;
import com.ricardo.munidenunciasapp.service.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List2Fragment extends Fragment {

    private static final String TAG = List2Fragment.class.getSimpleName();

    private RecyclerView denunciasList;
    private FloatingActionButton RegisterDenuncia;

    // SharedPreferences
    private SharedPreferences sharedPreferences;


    public List2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list2, container, false);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        RegisterDenuncia = (FloatingActionButton) view.findViewById(R.id.btnRegisterTienda);
        denunciasList = (RecyclerView) view.findViewById(R.id.recyclerview);
        denunciasList.setLayoutManager(new LinearLayoutManager(getActivity()));

        denunciasList.setAdapter(new DenunciasAdapter());

        RegisterDenuncia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                RegisterFragment fragment = new RegisterFragment();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        initialize();

        return view;
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Denuncia>> call = service.getDenuncias();

        call.enqueue(new Callback<List<Denuncia>>() {
            @Override
            public void onResponse(Call<List<Denuncia>> call, Response<List<Denuncia>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Denuncia> denuncias = response.body();
                        Log.d(TAG, "items: " + denuncias);

                        DenunciasAdapter adapter = (DenunciasAdapter) denunciasList.getAdapter();
                        adapter.setDenuncias(denuncias);
                        adapter.notifyDataSetChanged();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Denuncia>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
