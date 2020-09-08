package com.example.votingsystem.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class Contracts_Election_sol_Election extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061005f6040518060400160405280600b81526020016a43616e646964617465203160a81b8152506040518060400160405280600381526020016219191960ea1b8152506100b360201b60201c565b6100ae6040518060400160405280600b81526020016a21b0b73234b230ba32901960a91b815250604051806040016040528060048152602001636363636360e01b8152506100b360201b60201c565b6101d3565b600280546001908101918290556040805160808101825283815260208082018781528284018790526000606084018190529586528482529290942081518155915180519194929361010a9390850192910190610138565b5060408201518051610126916002840191602090910190610138565b50606082015181600301559050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061017957805160ff19168380011785556101a6565b828001600101855582156101a6579182015b828111156101a657825182559160200191906001019061018b565b506101b29291506101b6565b5090565b6101d091905b808211156101b257600081556001016101bc565b90565b6106d8806101e26000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80630121b93f1461005c57806326541b561461007b5780632d35a8a2146101a85780633477ee2e146101c2578063a3ec138d146102cb575b600080fd5b6100796004803603602081101561007257600080fd5b5035610305565b005b6100796004803603604081101561009157600080fd5b8101906020810181356401000000008111156100ac57600080fd5b8201836020820111156100be57600080fd5b803590602001918460018302840111640100000000831117156100e057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561013357600080fd5b82018360208201111561014557600080fd5b8035906020019184600183028401116401000000008311171561016757600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610429945050505050565b6101b06104ae565b60408051918252519081900360200190f35b6101df600480360360208110156101d857600080fd5b50356104b4565b604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b8381101561022c578181015183820152602001610214565b50505050905090810190601f1680156102595780820380516001836020036101000a031916815260200191505b50838103825285518152855160209182019187019080838360005b8381101561028c578181015183820152602001610274565b50505050905090810190601f1680156102b95780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b6102f1600480360360208110156102e157600080fd5b50356001600160a01b03166105f2565b604080519115158252519081900360200190f35b3360009081526020819052604090205460ff161561036a576040805162461bcd60e51b815260206004820152601d60248201527f796f75206861766520616c726561647920766f746564206265666f7265000000604482015290519081900360640190fd5b60008111801561037c57506002548111155b6103cd576040805162461bcd60e51b815260206004820152601e60248201527f746869732063616e646963617465206e6f206c6f6e6765722065786973740000604482015290519081900360640190fd5b33600090815260208181526040808320805460ff191660019081179091558484529182905280832060030180549092019091555182917ffff3c900d938d21d0990d786e819f29b8d05c1ef587b462b939609625b684b1691a250565b60028054600190810191829055604080516080810182528381526020808201878152828401879052600060608401819052958652848252929094208151815591518051919492936104809390850192910190610607565b506040820151805161049c916002840191602090910190610607565b50606082015181600301559050505050565b60025481565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f810185900485028601850190965285855290949193929091908301828280156105505780601f1061052557610100808354040283529160200191610550565b820191906000526020600020905b81548152906001019060200180831161053357829003601f168201915b50505060028085018054604080516020601f60001961010060018716150201909416959095049283018590048502810185019091528181529596959450909250908301828280156105e25780601f106105b7576101008083540402835291602001916105e2565b820191906000526020600020905b8154815290600101906020018083116105c557829003601f168201915b5050505050908060030154905084565b60006020819052908152604090205460ff1681565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061064857805160ff1916838001178555610675565b82800160010185558215610675579182015b8281111561067557825182559160200191906001019061065a565b50610681929150610685565b5090565b61069f91905b80821115610681576000815560010161068b565b9056fea26469706673582212203d8fbaf44203df21e5d9175034aee6fb49b65ac264d850cfafe13e42af03009064736f6c63430006060033";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final Event VOTEDEVENT_EVENT = new Event("votedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<VotedEventEventResponse> getVotedEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotedEventEventResponse> responses = new ArrayList<VotedEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VotedEventEventResponse typedResponse = new VotedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotedEventEventResponse>() {
            @Override
            public VotedEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDEVENT_EVENT, log);
                VotedEventEventResponse typedResponse = new VotedEventEventResponse();
                typedResponse.log = log;
                typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTEDEVENT_EVENT));
        return votedEventEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String _name, String _city) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new Utf8String(_name),
                new Utf8String(_city)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, String, String, BigInteger>> candidates(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, String, String, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, String, String, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> candidatesCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _candidateId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new Uint256(_candidateId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> voters(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class VotedEventEventResponse extends BaseEventResponse {
        public BigInteger _candidateId;
    }
}
