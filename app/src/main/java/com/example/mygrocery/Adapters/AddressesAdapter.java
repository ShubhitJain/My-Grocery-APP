package com.example.mygrocery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.addressesModel;
import com.example.mygrocery.views.MyAddresessActivity;

import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<ViewHilder> {
    /* access modifiers changed from: private */
    public int MODE;
    List<addressesModel> addressesModelList;
    /* access modifiers changed from: private */
    public int preSelectedPosition = -1;

    public AddressesAdapter(List<addressesModel> addressesModelList2, int MODE2) {
        this.addressesModelList = addressesModelList2;
        this.MODE = MODE2;
    }

    public ViewHilder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHilder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.addresses_item_layout, parent, false));
    }

    public void onBindViewHolder(ViewHilder holder, int position) {
        holder.setDetails(this.addressesModelList.get(position).getFullName(), this.addressesModelList.get(position).getAddress(), this.addressesModelList.get(position).getPincode(), this.addressesModelList.get(position).getSelected(), position);
    }

    public int getItemCount() {
        return this.addressesModelList.size();
    }

    public class ViewHilder extends RecyclerView.ViewHolder {
        TextView address;
        TextView fullName;
        private ImageView icon;
        /* access modifiers changed from: private */
        public LinearLayout option_cointainer;
        TextView pincode;

        public ViewHilder(View itemView) {
            super(itemView);
            this.fullName = (TextView) itemView.findViewById(C0181R.C0184id.addresses_full_name);
            this.address = (TextView) itemView.findViewById(C0181R.C0184id.addressesaddress);
            this.icon = (ImageView) itemView.findViewById(C0181R.C0184id.iconView);
            this.pincode = (TextView) itemView.findViewById(C0181R.C0184id.addresses_pincode);
            this.option_cointainer = (LinearLayout) itemView.findViewById(C0181R.C0184id.option_cointainer);
        }

        /* access modifiers changed from: private */
        public void setDetails(String name, String add, String pin, Boolean selected, final int position) {
            this.fullName.setText(name);
            this.address.setText(add);
            this.pincode.setText(pin);
            if (AddressesAdapter.this.MODE == 0) {
                this.icon.setImageResource(C0181R.C0183drawable.ic_baseline_check_24);
                if (selected.booleanValue()) {
                    this.icon.setVisibility(0);
                    int unused = AddressesAdapter.this.preSelectedPosition = position;
                } else {
                    this.icon.setVisibility(8);
                }
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (AddressesAdapter.this.preSelectedPosition != position) {
                            AddressesAdapter.this.addressesModelList.get(position).setSelected(true);
                            AddressesAdapter.this.addressesModelList.get(AddressesAdapter.this.preSelectedPosition).setSelected(false);
                            MyAddresessActivity.refreshItem(AddressesAdapter.this.preSelectedPosition, position);
                            int unused = AddressesAdapter.this.preSelectedPosition = position;
                        }
                    }
                });
            } else if (AddressesAdapter.this.MODE == 1) {
                this.option_cointainer.setVisibility(8);
                this.icon.setImageResource(C0181R.C0183drawable.ic_baseline_more_vert_24);
                this.icon.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ViewHilder.this.option_cointainer.setVisibility(0);
                        MyAddresessActivity.refreshItem(AddressesAdapter.this.preSelectedPosition, AddressesAdapter.this.preSelectedPosition);
                        int unused = AddressesAdapter.this.preSelectedPosition = position;
                    }
                });
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MyAddresessActivity.refreshItem(AddressesAdapter.this.preSelectedPosition, AddressesAdapter.this.preSelectedPosition);
                        int unused = AddressesAdapter.this.preSelectedPosition = -1;
                    }
                });
            }
        }
    }
}
