package android.swami.aks.inventorymanager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GetProductDetailFragment.OnGetProductFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GetProductDetailFragment} factory method to
 * create an instance of this fragment.
 */
public class GetProductDetailFragment extends Fragment {

    private OnGetProductFragmentInteractionListener mListener;

    public String barCode;
    public EditText productNameEditText;
    public TextView barCodeTextView;


    public GetProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.barCode = bundle.getString("barCode");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_product_detail, container, false);
        barCodeTextView = (TextView) view.findViewById(R.id.barCodeTextView);
        barCodeTextView.setText(this.barCode != null ? this.barCode : "");
        Log.d("INVENTORY::", this.barCode);
        productNameEditText = (EditText) view.findViewById(R.id.productNameText);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onGetProductFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnGetProductFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGetProductFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onGetProductFragmentInteraction(Uri uri);
    }

}
