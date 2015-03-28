package android.swami.aks.inventorymanager;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link android.swami.aks.inventorymanager.ScanBarCodeFragment.FragmentChangeListener} interface
 * to handle interaction events.
 * Use the {@link ScanBarCodeFragment} factory method to
 * create an instance of this fragment.
 */
public class ScanBarCodeFragment extends Fragment {

    public EditText barCodeTextView;
    public Button scanBarCodeButton;
    public Button nextButton;
    private String toast;

    private FragmentChangeListener mListener;

    public ScanBarCodeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scan_bar_code, container, false);
        scanBarCodeButton = (Button) view.findViewById(R.id.barCodeScabButton);
        barCodeTextView = (EditText) view.findViewById(R.id.barCodeTextField);
        scanBarCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanFromFragment();
            }
        });

        nextButton = (Button) view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNext();
            }
        });
        return view;
    }

    public void scanFromFragment() {
        IntentIntegrator.forFragment(this).initiateScan();
    }

    public void onClickNext(){
        Bundle bundle = new Bundle();
        bundle.putString("barCode", this.barCodeTextView.getText().toString());

        GetProductDetailFragment getProductDetailFragment = new GetProductDetailFragment();
        getProductDetailFragment.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(getProductDetailFragment);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                toast = "Rescan or Enter Manually";
            } else {
                toast = "Scanned Barcode: " + result.getContents();
                barCodeTextView.setText(result.getContents());
            }

            // At this point we may or may not have a reference to the activity
            displayToast();
        }
    }

    private void displayToast() {
        if (getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentChangeListener) activity;
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
    public interface FragmentChangeListener {
        public void replaceFragment(Fragment fragment);
    }

}
