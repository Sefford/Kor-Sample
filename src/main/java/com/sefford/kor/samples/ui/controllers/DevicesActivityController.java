package com.sefford.kor.samples.ui.controllers;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.adapters.RendererAdapter;
import com.sefford.brender.builder.Builder;
import com.sefford.brender.filters.DefaultAdapterData;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.core.business.InteractorCodes;
import com.sefford.kor.samples.core.business.InteractorFactory;
import com.sefford.kor.samples.core.business.errors.ScanError;
import com.sefford.kor.samples.core.business.responses.ScanResponse;
import com.sefford.kor.samples.core.executors.DataExecutor;
import com.sefford.kor.samples.core.interfaces.BusControllerInterface;
import com.sefford.kor.samples.ui.controllers.interfaces.DevicesActivityControllerInterface;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Default Implementation for Devices Fragment Controller
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class DevicesActivityController implements DevicesActivityControllerInterface {

    final BusControllerInterface bus;
    final Builder builder;
    final DataExecutor provider;
    final InteractorFactory factory;

    @InjectView(R.id.lv_data)
    ListView lvDevices;
    @InjectView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;

    List<Renderable> devices = new ArrayList<Renderable>();
    RendererAdapter adapter;
    SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    @Inject
    public DevicesActivityController(BusControllerInterface bus, Builder builder, DataExecutor provider, InteractorFactory factory) {
        this.bus = bus;
        this.builder = builder;
        this.provider = provider;
        this.factory = factory;
    }

    @Override
    public void bind(View view) {
        ButterKnife.inject(this, view);
        updateAdapter();
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!srlRefresh.isRefreshing()) {
                    srlRefresh.setRefreshing(true);
                    devices.clear();
                    adapter.notifyDataSetChanged();
                    provider.executeOperation(factory.generateInteractor(InteractorCodes.GET_DEVICES, null));
                }
            }
        };
        srlRefresh.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public void performRequest() {
        onRefreshListener.onRefresh();
    }

    @Override
    public void startListening() {
        bus.register(this);
    }

    @Override
    public void stopListening() {
        bus.unregister(this);
    }

    void updateAdapter() {
        if (adapter == null) {
            adapter = new RendererAdapter(new DefaultAdapterData(devices), builder, bus, null);
            lvDevices.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(ScanResponse response) {
        if (response.isSuccess()) {
            devices.addAll(response.getDevices());
            updateAdapter();
        }
        srlRefresh.setRefreshing(false);
    }

    public void onEventMainThread(ScanError error) {
        srlRefresh.setRefreshing(false);
    }
}
